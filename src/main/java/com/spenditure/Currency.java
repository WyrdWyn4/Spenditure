package com.spenditure;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class Currency {

    private Map<String, Double> exchangeRates;

    // Initialize exchange rates
    public Currency() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("AED - United Arab Emirates Dirham",0.2723);          // 1 AED = 0.2723 USD
        exchangeRates.put("AFN - Afghan Afghani", 0.013);                      // 1 AFN = 0.013 USD
        exchangeRates.put("ALL - Albanian Lek", 0.0093);                        // 1 ALL = 0.0093 USD
        exchangeRates.put("ANG - Netherlands Antillean Guilder", 0.002);        // 1 AMD = 0.002 USD
        exchangeRates.put("AMD - Armenian Dram", 0.56);                         // 1 ANG = 0.56 USD
        exchangeRates.put("AOA - Angolan Kwanza", 0.0016);                      // 1 AOA = 0.0016 USD
        exchangeRates.put("ARS - Argentine Peso", 0.011);                       // 1 ARS = 0.011 USD
        exchangeRates.put("AUD - Australian Dollar", 0.72);                     // 1 AUD = 0.72 USD
        exchangeRates.put("AWG - Aruban Flor", 0.56);                           // 1 AWG = 0.56 USD
        exchangeRates.put("AZN - Azerbaijani Manat", 0.59);                     // 1 AZN = 0.59 USD
        exchangeRates.put("BAM - Bosnia-Herzegovina Convertible Mark", 0.61);   // 1 BAM = 0.61 USD
        exchangeRates.put("BBD - Barbadian Dollar", 0.5);                       // 1 BBD = 0.5 USD
        exchangeRates.put("BDT - Bangladeshi Taka", 0.012);                     // 1 BDT = 0.012 USD
        exchangeRates.put("BGN - Bulgarian Lev", 0.61);                         // 1 BGN = 0.61 USD
        exchangeRates.put("BHD - Bahraini Dinar", 2.65);                        // 1 BHD = 2.65 USD
        exchangeRates.put("BIF - Burundian Franc", 0.0005);                     // 1 BIF = 0.0005 USD
        exchangeRates.put("BMD - Bermudian Dollar", 1.0);                       // 1 BMD = 1 USD
        exchangeRates.put("BND - Brunei Dollar", 0.72);                         // 1 BND = 0.72 USD
        exchangeRates.put("BOB - Bolivian Boliviano", 0.15);                    // 1 BOB = 0.15 USD
        exchangeRates.put("BRL - Brazilian Real", 0.18);                        // 1 BRL = 0.18 USD
        exchangeRates.put("BSD - Bahamian Dollar", 1.0);                        // 1 BSD = 1 USD
        exchangeRates.put("BTN - Bhutanese Ngultrum", 0.014);                   // 1 BTN = 0.014 USD
        exchangeRates.put("BWP - Botswana Pula", 0.088);                        // 1 BWP = 0.088 USD
        exchangeRates.put("BYN - Belarusian Ruble", 0.39);                      // 1 BYN = 0.39 USD
        exchangeRates.put("BZD - Belize Dollar", 0.5);                          // 1 BZD = 0.5 USD
        exchangeRates.put("CAD - Canadian Dollar", 0.79);                       // 1 CAD = 0.79 USD
        exchangeRates.put("CDF - Congolese Franc", 0.0005);                     // 1 CDF = 0.0005 USD
        exchangeRates.put("CHF - Swiss Franc", 1.1);                            // 1 CHF = 1.1 USD
        exchangeRates.put("CLP - Chilean Peso", 0.0013);                        // 1 CLP = 0.0013 USD
        exchangeRates.put("CNY - Chinese Yuan", 0.15);                          // 1 CNY = 0.15 USD
        exchangeRates.put("COP - Colombian Peso", 0.00027);                     // 1 COP = 0.00027 USD
        exchangeRates.put("CRC - Costa Rican Colón", 0.0016);                   // 1 CRC = 0.0016 USD
        exchangeRates.put("CUP - Cuban Peso", 0.037);                           // 1 CUP = 0.037 USD
        exchangeRates.put("CVE - Cape Verdean Escudo", 0.011);                  // 1 CVE = 0.011 USD
        exchangeRates.put("CZK - Czech Koruna", 0.046);                         // 1 CZK = 0.046 USD
        exchangeRates.put("DJF - Djiboutian Franc", 0.0056);                    // 1 DJF = 0.0056 USD
        exchangeRates.put("DKK - Danish Krone", 0.16);                          // 1 DKK = 0.16 USD
        exchangeRates.put("DOP - Dominican Peso", 0.017);                       // 1 DOP = 0.017 USD
        exchangeRates.put("DZD - Algerian Dinar", 0.0076);                      // 1 DZD = 0.0076 USD
        exchangeRates.put("EGP - Egyptian Pound", 0.064);                       // 1 EGP = 0.064 USD
        exchangeRates.put("ERN - Eritrean Nakfa", 0.066);                       // 1 ERN = 0.066 USD
        exchangeRates.put("ETB - Ethiopian Birr", 0.023);                       // 1 ETB = 0.023 USD
        exchangeRates.put("EUR - Euro", 1.21);                                  // 1 EUR = 1.21 USD
        exchangeRates.put("FJD - Fijian Dollar", 0.49);                         // 1 FJD = 0.49 USD
        exchangeRates.put("FKP - Falkland Islands Pound", 1.38);                // 1 FKP = 1.38 USD
        exchangeRates.put("FOK - Faroese Króna", 0.12);                         // 1 FOK = 0.12 USD
        exchangeRates.put("GBP - British Pound", 1.38);                         // 1 GBP = 1.38 USD
        exchangeRates.put("GEL - Georgian Lari", 0.31);                         // 1 GEL = 0.31 USD
        exchangeRates.put("GGP - Guernsey Pound", 1.38);                        // 1 GGP = 1.38 USD
        exchangeRates.put("GHS - Ghanaian Cedi", 0.17);                         // 1 GHS = 0.17 USD
        exchangeRates.put("GIP - Gibraltar Pound", 1.38);                       // 1 GIP = 1.38 USD
        exchangeRates.put("GMD - Gambian Dalasi", 0.019);                       // 1 GMD = 0.019 USD
        exchangeRates.put("GNF - Guinean Franc", 0.0001);                       // 1 GNF = 0.0001 USD
        exchangeRates.put("GTQ - Guatemalan Quetzal", 0.13);                    // 1 GTQ = 0.13 USD
        exchangeRates.put("GYD - Guyanese Dollar", 0.0047);                     // 1 GYD = 0.0047 USD
        exchangeRates.put("HKD - Hong Kong Dollar", 0.13);                      // 1 HKD = 0.13 USD
        exchangeRates.put("HNL - Honduran Lempira", 0.041);                     // 1 HNL = 0.041 USD
        exchangeRates.put("HRK - Croatian Kuna", 0.16);                         // 1 HRK = 0.16 USD
        exchangeRates.put("HTG - Haitian Gourde", 0.011);                       // 1 HTG = 0.011 USD
        exchangeRates.put("HUF - Hungarian Forint", 0.0033);                    // 1 HUF = 0.0033 USD
        exchangeRates.put("IDR - Indonesian Rupiah", 0.00007);                  // 1 IDR = 0.00007 USD
        exchangeRates.put("ILS - Israeli New Shekel", 0.31);                    // 1 ILS = 0.31 USD
        exchangeRates.put("IMP - Isle of Man Manx Pound", 1.38);                // 1 IMP = 1.38 USD
        exchangeRates.put("INR - Indian Rupee", 0.014);                         // 1 INR = 0.014 USD
        exchangeRates.put("IQD - Iraqi Dinar", 0.00068);                        // 1 IQD = 0.00068 USD
        exchangeRates.put("IRR - Iranian Rial", 0.000024);                      // 1 IRR = 0.000024 USD
        exchangeRates.put("ISK - Icelandic Króna", 0.0077);                     // 1 ISK = 0.0077 USD
        exchangeRates.put("JEP - Jersey Pound", 1.38);                          // 1 JEP = 1.38 USD
        exchangeRates.put("JMD - Jamaican Dollar", 0.0068);                     // 1 JMD = 0.0068 USD
        exchangeRates.put("JOD - Jordanian Dinar", 1.41);                       // 1 JOD = 1.41 USD
        exchangeRates.put("JPY - Japanese Yen", 0.0091);                        // 1 JPY = 0.0091 USD
        exchangeRates.put("KES - Kenyan Shilling", 0.0091);                     // 1 KES = 0.0091 USD
        exchangeRates.put("KGS - Kyrgyzstani Som", 0.011);                      // 1 KGS = 0.011 USD
        exchangeRates.put("KHR - Cambodian Riel", 0.00025);                     // 1 KHR = 0.00025 USD
        exchangeRates.put("KID - Kiribati Dollar", 0.72);                       // 1 KID = 0.72 USD
        exchangeRates.put("KMF - Comorian Franc", 0.0024);                      // 1 KMF = 0.0024 USD
        exchangeRates.put("KRW - South Korean Won", 0.0009);                    // 1 KRW = 0.0009 USD
        exchangeRates.put("KWD - Kuwaiti Dinar", 3.31);                         // 1 KWD = 3.31 USD
        exchangeRates.put("KYD - Cayman Islands Dollar", 1.2);                  // 1 KYD = 1.2 USD
        exchangeRates.put("KZT - Kazakhstani Tenge", 0.0023);                   // 1 KZT = 0.0023 USD
        exchangeRates.put("LAK - Lao Kip", 0.00011);                            // 1 LAK = 0.00011 USD
        exchangeRates.put("LBP - Lebanese Pound", 0.00066);                     // 1 LBP = 0.00066 USD
        exchangeRates.put("LKR - Sri Lankan Rupee", 0.005);                     // 1 LKR = 0.005 USD
        exchangeRates.put("LRD - Liberian Dollar", 0.0062);                     // 1 LRD = 0.0062 USD
        exchangeRates.put("LSL - Lesotho Loti", 0.066);                         // 1 LSL = 0.066 USD
        exchangeRates.put("LYD - Libyan Dinar", 0.22);                          // 1 LYD = 0.22 USD
        exchangeRates.put("MAD - Moroccan Dirham", 0.11);                       // 1 MAD = 0.11 USD
        exchangeRates.put("MDL - Moldovan Leu", 0.057);                         // 1 MDL = 0.057 USD
        exchangeRates.put("MGA - Malagasy Ariary", 0.00027);                    // 1 MGA = 0.00027 USD
        exchangeRates.put("MKD - Macedonian Denar", 0.019);                     // 1 MKD = 0.019 USD
        exchangeRates.put("MMK - Burmese Kyat", 0.00063);                       // 1 MMK = 0.00063 USD
        exchangeRates.put("MNT - Mongolian Tögrög", 0.00035);                   // 1 MNT = 0.00035 USD
        exchangeRates.put("MOP - Macanese Pataca", 0.12);                       // 1 MOP = 0.12 USD
        exchangeRates.put("MRU - Mauritanian Ouguiya", 0.028);                  // 1 MRU = 0.028 USD
        exchangeRates.put("MUR - Mauritian Rupee", 0.024);                      // 1 MUR = 0.024 USD
        exchangeRates.put("MVR - Maldivian Rufiyaa", 0.065);                    // 1 MVR = 0.065 USD
        exchangeRates.put("MWK - Malawian Kwacha", 0.0012);                     // 1 MWK = 0.0012 USD
        exchangeRates.put("MXN - Mexican Peso", 0.050);                         // 1 MXN = 0.050 USD
        exchangeRates.put("MZN - Mozambican Metical", 0.013);                   // 1 MZN = 0.013 USD
        exchangeRates.put("NAD - Namibian Dollar", 0.066);                      // 1 NAD = 0.066 USD
        exchangeRates.put("NGN - Nigerian Naira", 0.0024);                      // 1 NGN = 0.0024 USD
        exchangeRates.put("MYR - Malaysian Ringgit", 0.24);                     // 1 MYR = 0.24 USD
        exchangeRates.put("NIO - Nicaraguan Córdoba", 0.028);                   // 1 NIO = 0.028 USD
        exchangeRates.put("NOK - Norwegian Krone", 0.12);                       // 1 NOK = 0.12 USD
        exchangeRates.put("NPR - Nepalese Rupee", 0.0084);                      // 1 NPR = 0.0084 USD
        exchangeRates.put("NZD - New Zealand Dollar", 0.69);                    // 1 NZD = 0.69 USD
        exchangeRates.put("OMR - Omani Rial", 2.60);                            // 1 OMR = 2.60 USD
        exchangeRates.put("PAB - Panamanian Balboa", 1.0);                      // 1 PAB = 1 USD
        exchangeRates.put("PEN - Peruvian Sol", 0.26);                          // 1 PEN = 0.26 USD
        exchangeRates.put("PGK - Papua New Guinean Kina", 0.28);                // 1 PGK = 0.28 USD
        exchangeRates.put("PHP - Philippine Peso", 0.021);                      // 1 PHP = 0.021 USD
        exchangeRates.put("PKR - Pakistani Rupee", 0.0063);                     // 1 PKR = 0.0063 USD
        exchangeRates.put("PLN - Polish Złoty", 0.27);                          // 1 PLN = 0.27 USD
        exchangeRates.put("PYG - Paraguayan Guaraní", 0.00014);                 // 1 PYG = 0.00014 USD
        exchangeRates.put("QAR - Qatari Riyal", 0.27);                          // 1 QAR = 0.27 USD
        exchangeRates.put("RON - Romanian Leu", 0.25);                          // 1 RON = 0.25 USD
        exchangeRates.put("RSD - Serbian Dinar", 0.010);                        // 1 RSD = 0.010 USD
        exchangeRates.put("RUB - Russian Ruble", 0.013);                        // 1 RUB = 0.013 USD
        exchangeRates.put("RWF - Rwandan Franc", 0.0010);                       // 1 RWF = 0.0010 USD
        exchangeRates.put("SAR - Saudi Riyal", 0.27);                           // 1 SAR = 0.27 USD
        exchangeRates.put("SBD - Solomon Islands Dollar", 0.12);                // 1 SBD = 0.12 USD
        exchangeRates.put("SCR - Seychellois Rupee", 0.043);                    // 1 SCR = 0.043 USD
        exchangeRates.put("SDG - Sudanese Pound", 0.022);                       // 1 SDG = 0.022 USD
        exchangeRates.put("SEK - Swedish Krona", 0.12);                         // 1 SEK = 0.12 USD
        exchangeRates.put("SGD - Singapore Dollar", 0.72);                      // 1 SGD = 0.72 USD
        exchangeRates.put("SHP - Saint Helena Pound", 1.38);                    // 1 SHP = 1.38 USD
        exchangeRates.put("SLL - Sierra Leonean Leone", 0.00010);               // 1 SLL = 0.00010 USD
        exchangeRates.put("SOS - Somali Shilling", 0.0017);                     // 1 SOS = 0.0017 USD
        exchangeRates.put("SRD - Surinamese Dollar", 0.013);                    // 1 SRD = 0.013 USD
        exchangeRates.put("SSP - South Sudanese Pound", 0.0024);                // 1 SSP = 0.0024 USD
        exchangeRates.put("STN - São Tomé and Príncipe Dobra", 0.048);          // 1 STN = 0.048 USD
        exchangeRates.put("SYP - Syrian Pound", 0.00039);                       // 1 SYP = 0.00039 USD
        exchangeRates.put("SZL - Swazi Lilangeni", 0.066);                      // 1 SZL = 0.066 USD
        exchangeRates.put("THB - Thai Baht", 0.032);                            // 1 THB = 0.032 USD
        exchangeRates.put("TJS - Tajikistani Somoni", 0.089);                   // 1 TJS = 0.089 USD
        exchangeRates.put("TMT - Turkmenistan Manat", 0.28);                    // 1 TMT = 0.28 USD
        exchangeRates.put("TND - Tunisian Dinar", 0.36);                        // 1 TND = 0.36 USD
        exchangeRates.put("TOP - Tongan Paʻanga", 0.44);                        // 1 TOP = 0.44 USD
        exchangeRates.put("TRY - Turkish Lira", 0.12);                          // 1 TRY = 0.12 USD
        exchangeRates.put("TTD - Trinidad and Tobago Dollar", 0.15);            // 1 TTD = 0.15 USD
        exchangeRates.put("TVD - Tuvaluan Dollar", 0.72);                       // 1 TVD = 0.72 USD
        exchangeRates.put("TWD - New Taiwan Dollar", 0.036);                    // 1 TWD = 0.036 USD
        exchangeRates.put("TZS - Tanzanian Shilling", 0.00043);                 // 1 TZS = 0.00043 USD
        exchangeRates.put("UAH - Ukrainian Hryvnia", 0.036);                    // 1 UAH = 0.036 USD
        exchangeRates.put("UGX - Ugandan Shilling", 0.00028);                   // 1 UGX = 0.00028 USD
        exchangeRates.put("USD - United States Dollar", 1.0);                   // 1 USD = 1 USD
        exchangeRates.put("UYU - Uruguayan Peso", 0.023);                       // 1 UYU = 0.023 USD
        exchangeRates.put("UZS - Uzbekistan Som", 0.000094);                    // 1 UZS = 0.000094 USD
        exchangeRates.put("VES - Venezuelan Bolívar", 0.000028);                // 1 VES = 0.000028 USD
        exchangeRates.put("VND - Vietnamese Đồng", 0.000043);                   // 1 VND = 0.000043 USD
        exchangeRates.put("VUV - Vanuatu Vatu", 0.0091);                        // 1 VUV = 0.0091 USD
        exchangeRates.put("WST - Samoan Tala", 0.39);                           // 1 WST = 0.39 USD
        exchangeRates.put("XAF - Central African CFA Franc", 0.0018);           // 1 XAF = 0.0018 USD
        exchangeRates.put("XCD - East Caribbean Dollar", 0.37);                 // 1 XCD = 0.37 USD
        exchangeRates.put("XDR - Special Drawing Rights", 1.42);                // 1 XDR = 1.42 USD
        exchangeRates.put("XOF - West African CFA franc", 0.0018);              // 1 XOF = 0.0018 USD
        exchangeRates.put("XPF - CFP Franc", 0.0097);                           // 1 XPF = 0.0097 USD
        exchangeRates.put("YER - Yemeni Rial", 0.004);                          // 1 YER = 0.004 USD
        exchangeRates.put("ZAR - South African Rand", 0.066);                   // 1 ZAR = 0.066 USD
        exchangeRates.put("ZMW - Zambian Kwacha", 0.050);                       // 1 ZMW = 0.050 USD
        exchangeRates.put("ZWL - Zimbabwean Dollar", 0.0028);                   // 1 ZWL = 0.0028 USD
        // Add other exchange rates here...
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);
            return (amount * fromRate) / toRate ;
        }
        return amount;
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public Map<String, Double> searchExchangeRates(String keyword) {
        Map<String, Double> searchResults = new HashMap<>();
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            if (entry.getKey().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.put(entry.getKey(), entry.getValue());
            }
        }
        return searchResults;
    }

    public static String formatLargeNumber(double number) {
        String[] suffixes = new String[]{"", "K", "M", "B", "T"};
        double temp = Math.abs(number);
        int suffixIndex = 0;
        while (temp >= 1000 && suffixIndex < suffixes.length - 1) {
            suffixIndex++;
            temp /= 1000;
        }
        String formattedNumber = String.format("%.2f", temp);

        formattedNumber = NumberFormat.getInstance().format(Double.parseDouble(formattedNumber));
        if (number < 0) {
            formattedNumber = "-" + formattedNumber;
        }

        return formattedNumber + suffixes[suffixIndex];
    }
};


