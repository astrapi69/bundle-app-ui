package de.alpharogroup.bundle.app.spring;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import lombok.experimental.UtilityClass;

/**
 * A factory for creating DataObject objects.
 */
@UtilityClass
public class DataObjectFactory
{

	public static List<Countries> newAvailableCountries() {
		final Map<String, String> countriesMap = new LinkedHashMap<>();
		countriesMap.put("AE", "United Arab Emirates");
		countriesMap.put("JO", "Jordan");
		countriesMap.put("SY", "Syria");
		countriesMap.put("HR", "Croatia");
		countriesMap.put("BE", "Belgium");
		countriesMap.put("PA", "Panama");
		countriesMap.put("MT", "Malta");
		countriesMap.put("VE", "Venezuela");
		countriesMap.put("TW", "Taiwan");
		countriesMap.put("DK", "Denmark");
		countriesMap.put("PR", "Puerto Rico");
		countriesMap.put("VN", "Vietnam");
		countriesMap.put("US", "United States");
		countriesMap.put("ME", "Montenegro");
		countriesMap.put("SE", "Sweden");
		countriesMap.put("BO", "Bolivia");
		countriesMap.put("SG", "Singapore");
		countriesMap.put("BH", "Bahrain");
		countriesMap.put("SA", "Saudi Arabia");
		countriesMap.put("YE", "Yemen");
		countriesMap.put("IN", "India");
		countriesMap.put("MT", "Malta");
		countriesMap.put("FI", "Finland");
		countriesMap.put("BA", "Bosnia and Herzegovina");
		countriesMap.put("UA", "Ukraine");
		countriesMap.put("CH", "Switzerland");
		countriesMap.put("AR", "Argentina");
		countriesMap.put("EG", "Egypt");
		countriesMap.put("JP", "Japan");
		countriesMap.put("SV", "El Salvador");
		countriesMap.put("BR", "Brazil");
		countriesMap.put("IS", "Iceland");
		countriesMap.put("CZ", "Czech Republic");
		countriesMap.put("PL", "Poland");
		countriesMap.put("ES", "Spain");
		countriesMap.put("MY", "Malaysia");
		countriesMap.put("ES", "Spain");
		countriesMap.put("CO", "Colombia");
		countriesMap.put("BG", "Bulgaria");
		countriesMap.put("BA", "Bosnia and Herzegovina");
		countriesMap.put("PY", "Paraguay");
		countriesMap.put("EC", "Ecuador");
		countriesMap.put("US", "United States");
		countriesMap.put("SD", "Sudan");
		countriesMap.put("RO", "Romania");
		countriesMap.put("PH", "Philippines");
		countriesMap.put("TN", "Tunisia");
		countriesMap.put("ME", "Montenegro");
		countriesMap.put("GT", "Guatemala");
		countriesMap.put("KR", "South Korea");
		countriesMap.put("CY", "Cyprus");
		countriesMap.put("MX", "Mexico");
		countriesMap.put("RU", "Russia");
		countriesMap.put("HN", "Honduras");
		countriesMap.put("HK", "Hong Kong");
		countriesMap.put("NO", "Norway");
		countriesMap.put("HU", "Hungary");
		countriesMap.put("TH", "Thailand");
		countriesMap.put("IQ", "Iraq");
		countriesMap.put("CL", "Chile");
		countriesMap.put("MA", "Morocco");
		countriesMap.put("IE", "Ireland");
		countriesMap.put("TR", "Turkey");
		countriesMap.put("EE", "Estonia");
		countriesMap.put("QA", "Qatar");
		countriesMap.put("PT", "Portugal");
		countriesMap.put("LU", "Luxembourg");
		countriesMap.put("OM", "Oman");
		countriesMap.put("AL", "Albania");
		countriesMap.put("DO", "Dominican Republic");
		countriesMap.put("CU", "Cuba");
		countriesMap.put("NZ", "New Zealand");
		countriesMap.put("RS", "Serbia");
		countriesMap.put("CH", "Switzerland");
		countriesMap.put("UY", "Uruguay");
		countriesMap.put("GR", "Greece");
		countriesMap.put("IL", "Israel");
		countriesMap.put("ZA", "South Africa");
		countriesMap.put("TH", "Thailand");
		countriesMap.put("FR", "France");
		countriesMap.put("AT", "Austria");
		countriesMap.put("NO", "Norway");
		countriesMap.put("AU", "Australia");
		countriesMap.put("NL", "Netherlands");
		countriesMap.put("CA", "Canada");
		countriesMap.put("LV", "Latvia");
		countriesMap.put("LU", "Luxembourg");
		countriesMap.put("CR", "Costa Rica");
		countriesMap.put("KW", "Kuwait");
		countriesMap.put("LY", "Libya");
		countriesMap.put("CH", "Switzerland");
		countriesMap.put("DE", "Germany");
		countriesMap.put("DZ", "Algeria");
		countriesMap.put("SK", "Slovakia");
		countriesMap.put("LT", "Lithuania");
		countriesMap.put("IT", "Italy");
		countriesMap.put("IE", "Ireland");
		countriesMap.put("SG", "Singapore");
		countriesMap.put("CA", "Canada");
		countriesMap.put("BE", "Belgium");
		countriesMap.put("CN", "China");
		countriesMap.put("JP", "Japan");
		countriesMap.put("GR", "Greece");
		countriesMap.put("RS", "Serbia");
		countriesMap.put("IN", "India");
		countriesMap.put("LB", "Lebanon");
		countriesMap.put("NI", "Nicaragua");
		countriesMap.put("MK", "Macedonia");
		countriesMap.put("BY", "Belarus");
		countriesMap.put("SI", "Slovenia");
		countriesMap.put("PE", "Peru");
		countriesMap.put("ID", "Indonesia");
		countriesMap.put("GB", "United Kingdom");

		final List<Countries> countries = new ArrayList<>();
		for (final Map.Entry<String, String> entry : countriesMap.entrySet())
		{
			countries.add(
				Countries.builder().name(entry.getValue())
				.iso3166a2name(entry.getKey()).build());
		}
		return countries;
	}
	
	/**
	 * Factory method for create initial list of {@link Languages} objects.
	 *
	 * @return the created list with the {@link Languages} objects.
	 */
	public static List<Languages> newLanguages()
	{
		final Map<String, String> languagesMap = new LinkedHashMap<>();
		languagesMap.put("ab", "Abkhaz");
		languagesMap.put("aa", "Afar");
		languagesMap.put("af", "Afrikaans");
		languagesMap.put("ak", "Akan");
		languagesMap.put("sq", "Albanian");
		languagesMap.put("am", "Amharic");
		languagesMap.put("ar", "Arabic");
		languagesMap.put("an", "Aragonese");
		languagesMap.put("hy", "Armenian");
		languagesMap.put("as", "Assamese");
		languagesMap.put("av", "Avaric");
		languagesMap.put("ae", "Avestan");
		languagesMap.put("ay", "Aymara");
		languagesMap.put("az", "Azerbaijani");
		languagesMap.put("bm", "Bambara");
		languagesMap.put("ba", "Bashkir");
		languagesMap.put("eu", "Basque");
		languagesMap.put("be", "Belarusian");
		languagesMap.put("bn", "Bengali, Bangla");
		languagesMap.put("bh", "Bihari");
		languagesMap.put("bi", "Bislama");
		languagesMap.put("bs", "Bosnian");
		languagesMap.put("br", "Breton");
		languagesMap.put("bg", "Bulgarian");
		languagesMap.put("my", "Burmese");
		languagesMap.put("ca", "Catalan");
		languagesMap.put("ch", "Chamorro");
		languagesMap.put("ce", "Chechen");
		languagesMap.put("ny", "Chichewa, Chewa, Nyanja");
		languagesMap.put("zh", "Chinese");
		languagesMap.put("cv", "Chuvash");
		languagesMap.put("kw", "Cornish");
		languagesMap.put("co", "Corsican");
		languagesMap.put("cr", "Cree");
		languagesMap.put("hr", "Croatian");
		languagesMap.put("cs", "Czech");
		languagesMap.put("da", "Danish");
		languagesMap.put("dv", "Divehi, Dhivehi, Maldivian");
		languagesMap.put("nl", "Dutch");
		languagesMap.put("dz", "Dzongkha");
		languagesMap.put("en", "English");
		languagesMap.put("eo", "Esperanto");
		languagesMap.put("et", "Estonian");
		languagesMap.put("ee", "Ewe");
		languagesMap.put("fo", "Faroese");
		languagesMap.put("fj", "Fijian");
		languagesMap.put("fi", "Finnish");
		languagesMap.put("fr", "French");
		languagesMap.put("ff", "Fula, Fulah, Pulaar, Pular");
		languagesMap.put("gl", "Galician");
		languagesMap.put("ka", "Georgian");
		languagesMap.put("de", "German");
		languagesMap.put("el", "Greek (modern)");
		languagesMap.put("gn", "Guaraní");
		languagesMap.put("gu", "Gujarati");
		languagesMap.put("ht", "Haitian, Haitian Creole");
		languagesMap.put("ha", "Hausa");
		languagesMap.put("he", "Hebrew (modern)");
		languagesMap.put("hz", "Herero");
		languagesMap.put("hi", "Hindi");
		languagesMap.put("ho", "Hiri Motu");
		languagesMap.put("hu", "Hungarian");
		languagesMap.put("ia", "Interlingua");
		languagesMap.put("id", "Indonesian");
		languagesMap.put("ie", "Interlingue");
		languagesMap.put("ga", "Irish");
		languagesMap.put("ig", "Igbo");
		languagesMap.put("ik", "Inupiaq");
		languagesMap.put("io", "Ido");
		languagesMap.put("is", "Icelandic");
		languagesMap.put("it", "Italian");
		languagesMap.put("iu", "Inuktitut");
		languagesMap.put("ja", "Japanese");
		languagesMap.put("jv", "Javanese");
		languagesMap.put("kl", "Kalaallisut, Greenlandic");
		languagesMap.put("kn", "Kannada");
		languagesMap.put("kr", "Kanuri");
		languagesMap.put("ks", "Kashmiri");
		languagesMap.put("kk", "Kazakh");
		languagesMap.put("km", "Khmer");
		languagesMap.put("ki", "Kikuyu, Gikuyu");
		languagesMap.put("rw", "Kinyarwanda");
		languagesMap.put("ky", "Kyrgyz");
		languagesMap.put("kv", "Komi");
		languagesMap.put("kg", "Kongo");
		languagesMap.put("ko", "Korean");
		languagesMap.put("ku", "Kurdish");
		languagesMap.put("kj", "Kwanyama, Kuanyama");
		languagesMap.put("la", "Latin");
		languagesMap.put("lb", "Luxembourgish, Letzeburgesch");
		languagesMap.put("lg", "Ganda");
		languagesMap.put("li", "Limburgish, Limburgan, Limburger");
		languagesMap.put("ln", "Lingala");
		languagesMap.put("lo", "Lao");
		languagesMap.put("lt", "Lithuanian");
		languagesMap.put("lu", "Luba-Katanga");
		languagesMap.put("lv", "Latvian");
		languagesMap.put("gv", "Manx");
		languagesMap.put("mk", "Macedonian");
		languagesMap.put("mg", "Malagasy");
		languagesMap.put("ms", "Malay");
		languagesMap.put("ml", "Malayalam");
		languagesMap.put("mt", "Maltese");
		languagesMap.put("mi", "M?ori");
		languagesMap.put("mr", "Marathi");
		languagesMap.put("mh", "Marshallese");
		languagesMap.put("mn", "Mongolian");
		languagesMap.put("na", "Nauruan");
		languagesMap.put("nv", "Navajo, Navaho");
		languagesMap.put("nd", "Northern Ndebele");
		languagesMap.put("ne", "Nepali");
		languagesMap.put("ng", "Ndonga");
		languagesMap.put("nb", "Norwegian Bokmål");
		languagesMap.put("nn", "Norwegian Nynorsk");
		languagesMap.put("no", "Norwegian");
		languagesMap.put("ii", "Nuosu");
		languagesMap.put("nr", "Southern Ndebele");
		languagesMap.put("oc", "Occitan");
		languagesMap.put("oj", "Ojibwe, Ojibwa");
		languagesMap.put("cu", "Old Church Slavonic, Church Slavonic, Old Bulgarian");
		languagesMap.put("om", "Oromo");
		languagesMap.put("or", "Oriya");
		languagesMap.put("os", "Ossetian, Ossetic");
		languagesMap.put("pa", "Panjabi, Punjabi");
		languagesMap.put("pi", "P?li");
		languagesMap.put("fa", "Persian (Farsi)");
		languagesMap.put("pl", "Polish");
		languagesMap.put("ps", "Pashto, Pushto");
		languagesMap.put("pt", "Portuguese");
		languagesMap.put("qu", "Quechua");
		languagesMap.put("rm", "Romansh");
		languagesMap.put("rn", "Kirundi");
		languagesMap.put("rc", "Reunionese, Reunion Creole");
		languagesMap.put("ro", "Romanian");
		languagesMap.put("ru", "Russian");
		languagesMap.put("sa", "Sanskrit");
		languagesMap.put("sc", "Sardinian");
		languagesMap.put("sd", "Sindhi");
		languagesMap.put("se", "Northern Sami");
		languagesMap.put("sm", "Samoan");
		languagesMap.put("sg", "Sango");
		languagesMap.put("sr", "Serbian");
		languagesMap.put("gd", "Scottish Gaelic, Gaelic");
		languagesMap.put("sn", "Shona");
		languagesMap.put("si", "Sinhalese, Sinhala");
		languagesMap.put("sk", "Slovak");
		languagesMap.put("sl", "Slovene");
		languagesMap.put("so", "Somali");
		languagesMap.put("st", "Southern Sotho");
		languagesMap.put("es", "Spanish");
		languagesMap.put("su", "Sundanese");
		languagesMap.put("sw", "Swahili");
		languagesMap.put("ss", "Swati");
		languagesMap.put("sv", "Swedish");
		languagesMap.put("ta", "Tamil");
		languagesMap.put("te", "Telugu");
		languagesMap.put("tg", "Tajik");
		languagesMap.put("th", "Thai");
		languagesMap.put("ti", "Tigrinya");
		languagesMap.put("bo", "Tibetan Standard, Tibetan, Central");
		languagesMap.put("tk", "Turkmen");
		languagesMap.put("tl", "Tagalog");
		languagesMap.put("tn", "Tswana");
		languagesMap.put("to", "Tonga (Tonga Islands)");
		languagesMap.put("tr", "Turkish");
		languagesMap.put("ts", "Tsonga");
		languagesMap.put("tt", "Tatar");
		languagesMap.put("tw", "Twi");
		languagesMap.put("ty", "Tahitian");
		languagesMap.put("ug", "Uyghur");
		languagesMap.put("uk", "Ukrainian");
		languagesMap.put("ur", "Urdu");
		languagesMap.put("uz", "Uzbek");
		languagesMap.put("ve", "Venda");
		languagesMap.put("vi", "Vietnamese");
		languagesMap.put("vo", "Volapük");
		languagesMap.put("wa", "Walloon");
		languagesMap.put("cy", "Welsh");
		languagesMap.put("wo", "Wolof");
		languagesMap.put("fy", "Western Frisian");
		languagesMap.put("xh", "Xhosa");
		languagesMap.put("yi", "Yiddish");
		languagesMap.put("yo", "Yoruba");
		languagesMap.put("za", "Zhuang, Chuang");
		languagesMap.put("zu", "Zulu");

		final List<Languages> languages = new ArrayList<>();
		for (final Map.Entry<String, String> entry : languagesMap.entrySet())
		{
			languages.add(
				Languages.builder().name(entry.getValue()).iso639Dash1(entry.getKey()).build());
		}
		return languages;
	}

	/**
	 * Factory method for create initial list of {@link LanguageLocales} objects.
	 *
	 * @return the created list with the {@link LanguageLocales} objects.
	 */
	public static List<LanguageLocales> newLanguageLocales()
	{
		final List<LanguageLocales> languageLocales = new ArrayList<>();
		languageLocales.add(LanguageLocales.builder().locale("ar").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_AE").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_BH").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_DZ").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_EG").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_IQ").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_JO").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_KW").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_LB").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_LY").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_MA").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_OM").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_QA").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_SA").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_SD").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_SY").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_TN").build());
		languageLocales.add(LanguageLocales.builder().locale("ar_YE").build());
		languageLocales.add(LanguageLocales.builder().locale("be").build());
		languageLocales.add(LanguageLocales.builder().locale("be_BY").build());
		languageLocales.add(LanguageLocales.builder().locale("bg").build());
		languageLocales.add(LanguageLocales.builder().locale("bg_BG").build());
		languageLocales.add(LanguageLocales.builder().locale("ca").build());
		languageLocales.add(LanguageLocales.builder().locale("ca_ES").build());
		languageLocales.add(LanguageLocales.builder().locale("cs").build());
		languageLocales.add(LanguageLocales.builder().locale("cs_CZ").build());
		languageLocales.add(LanguageLocales.builder().locale("da").build());
		languageLocales.add(LanguageLocales.builder().locale("da_DK").build());
		languageLocales.add(LanguageLocales.builder().locale("de").build());
		languageLocales.add(LanguageLocales.builder().locale("de_AT").build());
		languageLocales.add(LanguageLocales.builder().locale("de_CH").build());
		languageLocales.add(LanguageLocales.builder().locale("de_DE").build());
		languageLocales.add(LanguageLocales.builder().locale("de_GR").build());
		languageLocales.add(LanguageLocales.builder().locale("de_LU").build());
		languageLocales.add(LanguageLocales.builder().locale("el").build());
		languageLocales.add(LanguageLocales.builder().locale("el_CY").build());
		languageLocales.add(LanguageLocales.builder().locale("el_GR").build());
		languageLocales.add(LanguageLocales.builder().locale("en").build());
		languageLocales.add(LanguageLocales.builder().locale("en_AU").build());
		languageLocales.add(LanguageLocales.builder().locale("en_CA").build());
		languageLocales.add(LanguageLocales.builder().locale("en_GB").build());
		languageLocales.add(LanguageLocales.builder().locale("en_IE").build());
		languageLocales.add(LanguageLocales.builder().locale("en_IN").build());
		languageLocales.add(LanguageLocales.builder().locale("en_MT").build());
		languageLocales.add(LanguageLocales.builder().locale("en_NZ").build());
		languageLocales.add(LanguageLocales.builder().locale("en_PH").build());
		languageLocales.add(LanguageLocales.builder().locale("en_SG").build());
		languageLocales.add(LanguageLocales.builder().locale("en_US").build());
		languageLocales.add(LanguageLocales.builder().locale("en_ZA").build());
		languageLocales.add(LanguageLocales.builder().locale("es").build());
		languageLocales.add(LanguageLocales.builder().locale("es_AR").build());
		languageLocales.add(LanguageLocales.builder().locale("es_BO").build());
		languageLocales.add(LanguageLocales.builder().locale("es_CL").build());
		languageLocales.add(LanguageLocales.builder().locale("es_CO").build());
		languageLocales.add(LanguageLocales.builder().locale("es_CR").build());
		languageLocales.add(LanguageLocales.builder().locale("es_CU").build());
		languageLocales.add(LanguageLocales.builder().locale("es_DO").build());
		languageLocales.add(LanguageLocales.builder().locale("es_EC").build());
		languageLocales.add(LanguageLocales.builder().locale("es_ES").build());
		languageLocales.add(LanguageLocales.builder().locale("es_GT").build());
		languageLocales.add(LanguageLocales.builder().locale("es_HN").build());
		languageLocales.add(LanguageLocales.builder().locale("es_MX").build());
		languageLocales.add(LanguageLocales.builder().locale("es_NI").build());
		languageLocales.add(LanguageLocales.builder().locale("es_PA").build());
		languageLocales.add(LanguageLocales.builder().locale("es_PE").build());
		languageLocales.add(LanguageLocales.builder().locale("es_PR").build());
		languageLocales.add(LanguageLocales.builder().locale("es_PY").build());
		languageLocales.add(LanguageLocales.builder().locale("es_SV").build());
		languageLocales.add(LanguageLocales.builder().locale("es_US").build());
		languageLocales.add(LanguageLocales.builder().locale("es_UY").build());
		languageLocales.add(LanguageLocales.builder().locale("es_VE").build());
		languageLocales.add(LanguageLocales.builder().locale("et").build());
		languageLocales.add(LanguageLocales.builder().locale("et_EE").build());
		languageLocales.add(LanguageLocales.builder().locale("fi").build());
		languageLocales.add(LanguageLocales.builder().locale("fi_FI").build());
		languageLocales.add(LanguageLocales.builder().locale("fr").build());
		languageLocales.add(LanguageLocales.builder().locale("fr_BE").build());
		languageLocales.add(LanguageLocales.builder().locale("fr_CA").build());
		languageLocales.add(LanguageLocales.builder().locale("fr_CH").build());
		languageLocales.add(LanguageLocales.builder().locale("fr_FR").build());
		languageLocales.add(LanguageLocales.builder().locale("fr_LU").build());
		languageLocales.add(LanguageLocales.builder().locale("ga").build());
		languageLocales.add(LanguageLocales.builder().locale("ga_IE").build());
		languageLocales.add(LanguageLocales.builder().locale("hi").build());
		languageLocales.add(LanguageLocales.builder().locale("hi_IN").build());
		languageLocales.add(LanguageLocales.builder().locale("hr").build());
		languageLocales.add(LanguageLocales.builder().locale("hr_HR").build());
		languageLocales.add(LanguageLocales.builder().locale("hu").build());
		languageLocales.add(LanguageLocales.builder().locale("hu_HU").build());
		languageLocales.add(LanguageLocales.builder().locale("in").build());
		languageLocales.add(LanguageLocales.builder().locale("in_ID").build());
		languageLocales.add(LanguageLocales.builder().locale("is").build());
		languageLocales.add(LanguageLocales.builder().locale("is_IS").build());
		languageLocales.add(LanguageLocales.builder().locale("it").build());
		languageLocales.add(LanguageLocales.builder().locale("it_CH").build());
		languageLocales.add(LanguageLocales.builder().locale("it_IT").build());
		languageLocales.add(LanguageLocales.builder().locale("iw").build());
		languageLocales.add(LanguageLocales.builder().locale("iw_IL").build());
		languageLocales.add(LanguageLocales.builder().locale("ja").build());
		languageLocales.add(LanguageLocales.builder().locale("ja_JP").build());
		languageLocales.add(LanguageLocales.builder().locale("ja_JP_JP").build());
		languageLocales.add(LanguageLocales.builder().locale("ko").build());
		languageLocales.add(LanguageLocales.builder().locale("ko_KR").build());
		languageLocales.add(LanguageLocales.builder().locale("lt").build());
		languageLocales.add(LanguageLocales.builder().locale("lt_LT").build());
		languageLocales.add(LanguageLocales.builder().locale("lv").build());
		languageLocales.add(LanguageLocales.builder().locale("lv_LV").build());
		languageLocales.add(LanguageLocales.builder().locale("mk").build());
		languageLocales.add(LanguageLocales.builder().locale("mk_MK").build());
		languageLocales.add(LanguageLocales.builder().locale("ms").build());
		languageLocales.add(LanguageLocales.builder().locale("ms_MY").build());
		languageLocales.add(LanguageLocales.builder().locale("mt").build());
		languageLocales.add(LanguageLocales.builder().locale("mt_MT").build());
		languageLocales.add(LanguageLocales.builder().locale("nl").build());
		languageLocales.add(LanguageLocales.builder().locale("nl_BE").build());
		languageLocales.add(LanguageLocales.builder().locale("nl_NL").build());
		languageLocales.add(LanguageLocales.builder().locale("no").build());
		languageLocales.add(LanguageLocales.builder().locale("no_NO").build());
		languageLocales.add(LanguageLocales.builder().locale("no_NO_NY").build());
		languageLocales.add(LanguageLocales.builder().locale("pl").build());
		languageLocales.add(LanguageLocales.builder().locale("pl_PL").build());
		languageLocales.add(LanguageLocales.builder().locale("pt").build());
		languageLocales.add(LanguageLocales.builder().locale("pt_BR").build());
		languageLocales.add(LanguageLocales.builder().locale("pt_PT").build());
		languageLocales.add(LanguageLocales.builder().locale("ro").build());
		languageLocales.add(LanguageLocales.builder().locale("ro_RO").build());
		languageLocales.add(LanguageLocales.builder().locale("ru").build());
		languageLocales.add(LanguageLocales.builder().locale("ru_RU").build());
		languageLocales.add(LanguageLocales.builder().locale("sk").build());
		languageLocales.add(LanguageLocales.builder().locale("sk_SK").build());
		languageLocales.add(LanguageLocales.builder().locale("sl").build());
		languageLocales.add(LanguageLocales.builder().locale("sl_SI").build());
		languageLocales.add(LanguageLocales.builder().locale("sq").build());
		languageLocales.add(LanguageLocales.builder().locale("sq_AL").build());
		languageLocales.add(LanguageLocales.builder().locale("sr").build());
		languageLocales.add(LanguageLocales.builder().locale("sr_BA").build());
		languageLocales.add(LanguageLocales.builder().locale("sr_CS").build());
		languageLocales.add(LanguageLocales.builder().locale("sr_ME").build());
		languageLocales.add(LanguageLocales.builder().locale("sr_RS").build());
		languageLocales.add(LanguageLocales.builder().locale("sv").build());
		languageLocales.add(LanguageLocales.builder().locale("sv_SE").build());
		languageLocales.add(LanguageLocales.builder().locale("th").build());
		languageLocales.add(LanguageLocales.builder().locale("th_TH").build());
		languageLocales.add(LanguageLocales.builder().locale("th_TH_TH").build());
		languageLocales.add(LanguageLocales.builder().locale("tr").build());
		languageLocales.add(LanguageLocales.builder().locale("tr_TR").build());
		languageLocales.add(LanguageLocales.builder().locale("uk").build());
		languageLocales.add(LanguageLocales.builder().locale("uk_UA").build());
		languageLocales.add(LanguageLocales.builder().locale("vi").build());
		languageLocales.add(LanguageLocales.builder().locale("vi_VN").build());
		languageLocales.add(LanguageLocales.builder().locale("zh").build());
		languageLocales.add(LanguageLocales.builder().locale("zh_CN").build());
		languageLocales.add(LanguageLocales.builder().locale("zh_HK").build());
		languageLocales.add(LanguageLocales.builder().locale("zh_SG").build());
		languageLocales.add(LanguageLocales.builder().locale("zh_TW").build());
		return languageLocales;
	}
}
