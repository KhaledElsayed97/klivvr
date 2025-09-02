package dev.khaled.klivvr.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.khaled.klivvr.domain.model.CityDomainModel

@Composable
fun CityItem(
    city: CityDomainModel, 
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 48.dp, top = 4.dp, bottom = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = getCountryFlag(city.country),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${city.name}, ${city.country}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "${city.lat}, ${city.lon}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun getCountryFlag(countryCode: String): String {
    return when (countryCode.uppercase()) {
        "US" -> "🇺🇸"
        "CA" -> "🇨🇦"
        "GB" -> "🇬🇧"
        "FR" -> "🇫🇷"
        "DE" -> "🇩🇪"
        "IT" -> "🇮🇹"
        "ES" -> "🇪🇸"
        "RU" -> "🇷🇺"
        "CN" -> "🇨🇳"
        "JP" -> "🇯🇵"
        "KR" -> "🇰🇷"
        "IN" -> "🇮🇳"
        "BR" -> "🇧🇷"
        "AU" -> "🇦🇺"
        "MX" -> "🇲🇽"
        "AR" -> "🇦🇷"
        "EG" -> "🇪🇬"
        "ZA" -> "🇿🇦"
        "NG" -> "🇳🇬"
        "KE" -> "🇰🇪"
        "MA" -> "🇲🇦"
        "DZ" -> "🇩🇿"
        "TN" -> "🇹🇳"
        "LY" -> "🇱🇾"
        "SD" -> "🇸🇩"
        "ET" -> "🇪🇹"
        "UG" -> "🇺🇬"
        "TZ" -> "🇹🇿"
        "GH" -> "🇬🇭"
        "CI" -> "🇨🇮"
        "SN" -> "🇸🇳"
        "ML" -> "🇲🇱"
        "BF" -> "🇧🇫"
        "NE" -> "🇳🇪"
        "TD" -> "🇹🇩"
        "CF" -> "🇨🇫"
        "CM" -> "🇨🇲"
        "GA" -> "🇬🇦"
        "CG" -> "🇨🇬"
        "CD" -> "🇨🇩"
        "AO" -> "🇦🇴"
        "ZM" -> "🇿🇲"
        "ZW" -> "🇿🇼"
        "BW" -> "🇧🇼"
        "NA" -> "🇳🇦"
        "SZ" -> "🇸🇿"
        "LS" -> "🇱🇸"
        "MW" -> "🇲🇼"
        "MZ" -> "🇲🇿"
        "MG" -> "🇲🇬"
        "MU" -> "🇲🇺"
        "SC" -> "🇸🇨"
        "KM" -> "🇰🇲"
        "DJ" -> "🇩🇯"
        "SO" -> "🇸🇴"
        "ER" -> "🇪🇷"
        "SS" -> "🇸🇸"
        "RW" -> "🇷🇼"
        "BI" -> "🇧🇮"
        "MW" -> "🇲🇼"
        "ZM" -> "🇿🇲"
        "ZW" -> "🇿🇼"
        "BW" -> "🇧🇼"
        "NA" -> "🇳🇦"
        "SZ" -> "🇸🇿"
        "LS" -> "🇱🇸"
        "MW" -> "🇲🇼"
        "MZ" -> "🇲🇿"
        "MG" -> "🇲🇬"
        "MU" -> "🇲🇺"
        "SC" -> "🇸🇨"
        "KM" -> "🇰🇲"
        "DJ" -> "🇩🇯"
        "SO" -> "🇸🇴"
        "ER" -> "🇪🇷"
        "SS" -> "🇸🇸"
        "RW" -> "🇷🇼"
        "BI" -> "🇧🇮"
        "UA" -> "🇺🇦"
        "PL" -> "🇵🇱"
        "CZ" -> "🇨🇿"
        "SK" -> "🇸🇰"
        "HU" -> "🇭🇺"
        "RO" -> "🇷🇴"
        "BG" -> "🇧🇬"
        "HR" -> "🇭🇷"
        "SI" -> "🇸🇮"
        "BA" -> "🇧🇦"
        "RS" -> "🇷🇸"
        "ME" -> "🇲🇪"
        "MK" -> "🇲🇰"
        "AL" -> "🇦🇱"
        "GR" -> "🇬🇷"
        "TR" -> "🇹🇷"
        "CY" -> "🇨🇾"
        "MT" -> "🇲🇹"
        "IE" -> "🇮🇪"
        "IS" -> "🇮🇸"
        "NO" -> "🇳🇴"
        "SE" -> "🇸🇪"
        "FI" -> "🇫🇮"
        "DK" -> "🇩🇰"
        "NL" -> "🇳🇱"
        "BE" -> "🇧🇪"
        "LU" -> "🇱🇺"
        "CH" -> "🇨🇭"
        "AT" -> "🇦🇹"
        "LI" -> "🇱🇮"
        "MC" -> "🇲🇨"
        "AD" -> "🇦🇩"
        "SM" -> "🇸🇲"
        "VA" -> "🇻🇦"
        "PT" -> "🇵🇹"
        "NP" -> "🇳🇵"
        "BD" -> "🇧🇩"
        "LK" -> "🇱🇰"
        "MV" -> "🇲🇻"
        "BT" -> "🇧🇹"
        "MM" -> "🇲🇲"
        "TH" -> "🇹🇭"
        "LA" -> "🇱🇦"
        "KH" -> "🇰🇭"
        "VN" -> "🇻🇳"
        "MY" -> "🇲🇾"
        "SG" -> "🇸🇬"
        "BN" -> "🇧🇳"
        "ID" -> "🇮🇩"
        "TL" -> "🇹🇱"
        "PH" -> "🇵🇭"
        "TW" -> "🇹🇼"
        "HK" -> "🇭🇰"
        "MO" -> "🇲🇴"
        "MN" -> "🇲🇳"
        "KZ" -> "🇰🇿"
        "UZ" -> "🇺🇿"
        "TM" -> "🇹🇲"
        "TJ" -> "🇹🇯"
        "KG" -> "🇰🇬"
        "AF" -> "🇦🇫"
        "PK" -> "🇵🇰"
        "IR" -> "🇮🇷"
        "IQ" -> "🇮🇶"
        "SY" -> "🇸🇾"
        "LB" -> "🇱🇧"
        "JO" -> "🇯🇴"
        "IL" -> "🇮🇱"
        "PS" -> "🇵🇸"
        "SA" -> "🇸🇦"
        "YE" -> "🇾🇪"
        "OM" -> "🇴🇲"
        "AE" -> "🇦🇪"
        "QA" -> "🇶🇦"
        "BH" -> "🇧🇭"
        "KW" -> "🇰🇼"
        "VE" -> "🇻🇪"
        "CO" -> "🇨🇴"
        "PE" -> "🇵🇪"
        "EC" -> "🇪🇨"
        "BO" -> "🇧🇴"
        "PY" -> "🇵🇾"
        "UY" -> "🇺🇾"
        "CL" -> "🇨🇱"
        "GY" -> "🇬🇾"
        "SR" -> "🇸🇷"
        "GF" -> "🇬🇫"
        "FK" -> "🇫🇰"
        "GS" -> "🇬🇸"
        "NZ" -> "🇳🇿"
        "FJ" -> "🇫🇯"
        "PG" -> "🇵🇬"
        "SB" -> "🇸🇧"
        "VU" -> "🇻🇺"
        "NC" -> "🇳🇨"
        "PF" -> "🇵🇫"
        "WS" -> "🇼🇸"
        "TO" -> "🇹🇴"
        "KI" -> "🇰🇮"
        "TV" -> "🇹🇻"
        "NR" -> "🇳🇷"
        "PW" -> "🇵🇼"
        "FM" -> "🇫🇲"
        "MH" -> "🇲🇭"
        "MP" -> "🇲🇵"
        "GU" -> "🇬🇺"
        "AS" -> "🇦🇸"
        "VI" -> "🇻🇮"
        "PR" -> "🇵🇷"
        "DO" -> "🇩🇴"
        "HT" -> "🇭🇹"
        "CU" -> "🇨🇺"
        "JM" -> "🇯🇲"
        "BS" -> "🇧🇸"
        "BB" -> "🇧🇧"
        "TT" -> "🇹🇹"
        "AG" -> "🇦🇬"
        "DM" -> "🇩🇲"
        "GD" -> "🇬🇩"
        "LC" -> "🇱🇨"
        "VC" -> "🇻🇨"
        "KN" -> "🇰🇳"
        "BZ" -> "🇧🇿"
        "GT" -> "🇬🇹"
        "SV" -> "🇸🇻"
        "HN" -> "🇭🇳"
        "NI" -> "🇳🇮"
        "CR" -> "🇨🇷"
        "PA" -> "🇵🇦"
        else -> "🏳️"
    }
}
