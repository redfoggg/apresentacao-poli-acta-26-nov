using System.Text.Json.Serialization;
using cc_bank.Models;

namespace cc_bank.Out;

public record TransactionDto(
       [property: JsonPropertyNameAttribute("transaction-id")] Guid Id,
       [property: JsonPropertyNameAttribute("value")] string Value,
       [property: JsonPropertyNameAttribute("category")] Category Category)
{
    public TransactionDto(Transaction transaction)
        : this(transaction.Id, $"R$ {transaction.Value.ToString("0.00")}", transaction.Category)
    { }
};
