namespace cc_bank.Models;

public class Transaction 
{
    public Guid Id { get; private set; }
    
    public Category Category { get; private set; }

    public decimal Value { get; private set; }

    public Transaction(Category category, decimal value)
    {
        Id = Guid.NewGuid();
        Category = category;
        Value = value;
    }
}

public enum Category 
{
    None,
    Health,
    Transportation,
    Food
}
