namespace cc_bank.Models;

public class Feed
{
    public Guid CustomerId { get; init; }

    public List<Transaction> Transactions { get; private set; }

    public decimal DueValue { get => Transactions.Sum(t => t.Value); }

    public Feed(Guid customerId, List<Transaction>? transactions = null)
    {
        CustomerId = customerId;
        Transactions = transactions is null
            ? new List<Transaction>()
            : transactions;
    }

    public void AddTransaction(Transaction transaction) => Transactions.Add(transaction);

    public void AddTransaction(List<Transaction> transactions) => Transactions.AddRange(transactions);
}
