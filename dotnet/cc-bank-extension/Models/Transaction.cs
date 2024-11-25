namespace cc_bank.Models;

public class Transaction 
{
    public Guid Id { get; private set; }
    
    public Category Category { get; private set; }

    public decimal Value { get; private set; }

    public bool HasError => Value < 0;

    public decimal NormalizedValue => Value > 0 ? Value : 0;

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
