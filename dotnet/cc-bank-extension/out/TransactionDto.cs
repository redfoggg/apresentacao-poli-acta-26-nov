using System.Text.Json.Serialization;
using cc_bank.Models;

namespace cc_bank.Out;

public record TransactionDto
{

    [JsonPropertyName("transaction-id")]
    public Guid Id { get; init; }

    [JsonPropertyName("value")]
    public string Value { get; init; }
    
    [JsonPropertyName("category")]
    public Category Category { get; init; }

    public TransactionDto(Transaction transaction)
    {
        Id = transaction.Id;
        Value = transaction.HasError ? "Algo deu errado na sua transação" : $"R$ {transaction.Value.ToString("0.00")}";
        Category = transaction.Category;
    }
};
