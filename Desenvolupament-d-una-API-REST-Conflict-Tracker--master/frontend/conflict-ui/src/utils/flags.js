export function getFlagUrl(countryCode) {
    if (!countryCode) return "";

    return `https://flagcdn.com/w40/${countryCode.toLowerCase()}.png`;
}