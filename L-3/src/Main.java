import java.util.List;
import java.util.ArrayList;

class Credit {
    private String name;
    private double amount;
    private double interestRate;
    private int term;

    // Конструктор
    public Credit(String name, double amount, double interestRate, int term) {
        this.name = name;
        this.amount = amount;
        this.interestRate = interestRate;
        this.term = term;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    // Метод для расчета суммы погашения
    public double calculateRepaymentAmount() {
        return amount * (1 + interestRate / 100);
    }

    // Метод для увеличения кредитной линии
    public void increaseCreditLine(double additionalAmount) {
        this.amount += additionalAmount;
    }

    // Метод для досрочного погашения кредита
    public void repayLoan(double amountToRepay) {
        this.amount -= amountToRepay;
    }
}

class CreditRecord {
    private String customerLastName;
    private String bankName;
    private Credit credit;

    // Конструктор
    public CreditRecord(String customerLastName, String bankName, Credit credit) {
        this.customerLastName = customerLastName;
        this.bankName = bankName;
        this.credit = credit;
    }

    // Геттеры и сеттеры
    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}

class Bank {
    private List<CreditRecord> creditRecords;

    // Конструктор
    public Bank() {
        this.creditRecords = new ArrayList<>();
    }

    // Метод для добавления новой записи о кредите
    public void addCreditRecord(CreditRecord creditRecord) {
        this.creditRecords.add(creditRecord);
    }

    // Метод для получения списка записей о кредитах
    public List<CreditRecord> getCreditRecords() {
        return creditRecords;
    }

    // Метод для поиска кредита по фамилии клиента
    public Credit findCreditByCustomerLastName(String customerLastName) {
        for (CreditRecord creditRecord : creditRecords) {
            if (creditRecord.getCustomerLastName().equals(customerLastName)) {
                return creditRecord.getCredit();
            }
        }
        return null;
    }
}

class Customer {
    private String firstName;
    private String lastName;
    private int age;

    // Конструктор
    public Customer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Геттеры и сеттеры
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Перегрузка метода toString для вывода информации о клиенте
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем объекты классов Customer и Bank
        Customer customer1 = new Customer("Иван", "Смирнов", 30);
        Customer customer2 = new Customer("Дарья", "Трофимова", 43);
        Bank bank = new Bank();

        // Создаем объекты класса Credit
        Credit credit1 = new Credit("Потребительский кредит", 10000, 10, 12);
        Credit credit2 = new Credit("Ипотека", 200000, 5, 122);

        // Добавляем записи о кредитах в банк
        bank.addCreditRecord(new CreditRecord(customer1.getLastName(), "Bank A", credit1));
        bank.addCreditRecord(new CreditRecord(customer2.getLastName(), "Bank A", credit2));

        // Поиск кредита по фамилии клиента
        Credit creditForCustomer1 = bank.findCreditByCustomerLastName(customer1.getLastName());
        Credit creditForCustomer2 = bank.findCreditByCustomerLastName(customer2.getLastName());

        if (creditForCustomer1 != null) {
            System.out.println("Клиент - " + customer1.getLastName() + ":");
            System.out.println("Название: " + creditForCustomer1.getName());
            System.out.println("Сумма к возврату: " + creditForCustomer1.calculateRepaymentAmount());
            System.out.println("Срок: " + creditForCustomer1.getTerm());
        } else {
            System.out.println("Кредит для клиента " + customer1.getLastName() + " не найден.");
        }

        if (creditForCustomer2 != null) {
            System.out.println("Клиент - " + customer2.getLastName() + ":");
            System.out.println("Название: " + creditForCustomer2.getName());
            System.out.println("Сумма к возврату: " + creditForCustomer2.calculateRepaymentAmount());
            System.out.println("Срок: " + creditForCustomer2.getTerm());
        } else {
            System.out.println("Кредит для клиента " + customer2.getLastName() + " не найден.");
        }
    }
}
