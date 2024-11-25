using System.Text.Json;
using System.Text.Json.Serialization;
using In = cc_bank.In;
using Out = cc_bank.Out;
using cc_bank.Models;

var jsonOptions = new JsonSerializerOptions { WriteIndented = true };
jsonOptions.Converters.Add(new JsonStringEnumConverter());

Feed? feed = null;

while (true)
{
    var input = Console.ReadLine();

    if (string.IsNullOrEmpty(input))
    {
        Console.WriteLine("Exit");
        break;
    }

    var transactionsDto = JsonSerializer.Deserialize<In.TransactionDto[]>(input, jsonOptions);

    var transactionList = transactionsDto!.Select(tdto => tdto.ToTransaction()).ToList();

    if (feed is null)
        feed = new Feed(Guid.NewGuid(), transactionList);
    else
        feed.AddTransaction(transactionList);

    var response = new Out.FeedDto(feed);

    Console.WriteLine(JsonSerializer.Serialize(response, jsonOptions));
}
