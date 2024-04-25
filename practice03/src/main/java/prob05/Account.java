package prob05;

public class Account {
	private String accountNo;
	private long balance;

	public Account(String accountNo) {
		System.out.println(accountNo + " 계좌가 개설되었습니다.");
		this.accountNo = accountNo;
	}

	public void save(int balance) {
		System.out.println(this.accountNo + " 계좌에 " + balance + "만원이 입금되었습니다.");
		this.balance += balance;
	}

	public void deposit(int balance) {
		System.out.println(this.accountNo + " 계좌에 " + balance + "만원이 출금되었습니다.");
		this.balance -= balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

}
