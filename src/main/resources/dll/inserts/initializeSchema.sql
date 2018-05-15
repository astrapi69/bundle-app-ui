
    create table basenames (
       id int4 not null,
        version int4,
        name varchar(512),
        primary key (id)
    );
create table bundle_application_language_locales (
       application_id int4 not null,
        language_locales_id int4 not null,
        primary key (application_id, language_locales_id)
    );
create table bundle_applications (
       id int4 not null,
        version int4,
        name varchar(1024) unique,
        default_locale_id int4,
        primary key (id)
    );
create table bundlenames (
       id int4 not null,
        version int4,
        filepath varchar(4096),
        base_name_id int4,
        locale_id int4,
        owner_id int4,
        primary key (id)
    );
create table countries (
       id int4 not null,
        name varchar(64) unique,
        iso3166_a2name varchar(2),
        primary key (id)
    );
create table language_locales (
       id int4 not null,
        version int4,
        locale varchar(64) unique,
        primary key (id)
    );
create table languages (
       id int4 not null,
        version int4,
        name varchar(64) unique,
        iso639_1 varchar(2),
        primary key (id)
    );
create table properties_keys (
       id int4 not null,
        version int4,
        name varchar(1024),
        primary key (id)
    );
create table properties_values (
       id int4 not null,
        version int4,
        name varchar(4096) unique,
        primary key (id)
    );
create table resourcebundles (
       id int4 not null,
        version int4,
        bundlename_id int4,
        properties_key_id int4,
        properties_value_id int4,
        primary key (id)
    );


alter table bundle_application_language_locales add constraint FKC6BB891EFB766A66 foreign key (language_locales_id) references language_locales;
alter table bundle_application_language_locales add constraint FKC6BB891E64F45D92 foreign key (application_id) references bundle_applications;
alter table bundle_applications add constraint FK176B41C017DEE600 foreign key (default_locale_id) references language_locales;
alter table bundlenames add constraint FKF230A806D4CC327E foreign key (locale_id) references language_locales;
alter table bundlenames add constraint FKF230A80614CE514F foreign key (owner_id) references bundle_applications;
alter table bundlenames add constraint FKF230A80663C76715 foreign key (base_name_id) references basenames;
alter table resourcebundles add constraint FKD0A7106365054731 foreign key (properties_key_id) references properties_keys;
alter table resourcebundles add constraint FKD0A71063BE71D570 foreign key (bundlename_id) references bundlenames;
alter table resourcebundles add constraint FKD0A71063D970898D foreign key (properties_value_id) references properties_values;
create sequence hibernate_sequence;
