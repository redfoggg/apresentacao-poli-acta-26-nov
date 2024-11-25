using System.Text.Json.Serialization;
using cc_bank.Models;

namespace cc_bank.In;

public record TransactionDto(
       [property: JsonPropertyNameAttribute("value")] decimal Value,
       [property: JsonPropertyNameAttribute("category")] Category Category)
{
    public Transaction ToTransaction() => new Transaction(Category, Value);
};
