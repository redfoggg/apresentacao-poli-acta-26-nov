using System.Text.Json.Serialization;
using cc_bank.Models;

namespace cc_bank.Out;

public record FeedDto
{
    [JsonPropertyName("due-value")]
    public string DueValue { get; init; }

    [JsonPropertyName("transactions")]
    public TransactionDto[] Transactions { get; init; }

    [JsonPropertyName("customer-id")]
    public Guid CustomerId { get; init; }

    public FeedDto(Feed feed)
    {
        DueValue = $"R$ {feed.DueValue.ToString("0.00")}";
        CustomerId = feed.CustomerId;
        Transactions = feed.Transactions.Select(t => new TransactionDto(t)).ToArray();
    }
}
