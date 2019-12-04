package de.alpharogroup.bundlemanagement.viewmodel;

import java.util.Locale;
import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ImprortableBundleName
{
	String baseName;
	String bundleappname;
	String filepath;
	Locale locale;
	Properties properties;
}
