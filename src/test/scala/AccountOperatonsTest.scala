package com.knoldus
import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.Map
class AccountOperatonsTest extends AnyFunSuite {
  val accountOperationsObject= new AccountOperations
  test("createAccount method should add the new account number to accounts Map") {
    val openingBalanceAmount = 10000.43
    val AccountList: Map[Long, Double] = accountOperationsObject.createAccount(openingBalanceAmount)
    assert(accountOperationsObject.AccountNumber_Balance.head._1 == AccountList.head._1)

  }
  test("listAccounts method should list the same number of accounts as are in list") {
    assert(accountOperationsObject.AccountNumber_Balance.size == accountOperationsObject.listAccounts().size)
  }

  test("fetchAccountBalance method should fetch the balance of corresponding account number") {
    val creatingAccount = accountOperationsObject.createAccount(10000.43)
    // extracting the key (random number) generated for new account
    val getAccount = creatingAccount.head._1
    val balanceAmount = accountOperationsObject.fetchAccountBalance(getAccount)
    // result should return the opening balance passed = 10000.43
    assert(balanceAmount == 10000.43)
  }

  test("fetchAccountBalance method should return -1 for a non-existent account number") {
    val creatingAccount = accountOperationsObject.createAccount(10000.43)
    //Account number tested here is more than that random function can generate
    assert(accountOperationsObject.fetchAccountBalance(100000L) == -1)
  }

  test("updateBalance method should update the account balance based on the list of transactions") {

    val accountNumber = accountOperationsObject.createAccount(23540.11).head._1
    val transactionsList = List(
      Transactions(1000L, accountNumber, "Debit", 1500.22)
    )
    accountOperationsObject.updateBalance(transactionsList)
    assert(accountOperationsObject.AccountNumber_Balance(accountNumber) == 22039.89)
  }
  test("deleteAccount should remove account from the accounts map") {
    val accountNumber = 23782L
    val initialAccountBalance = 5000.0
    accountOperationsObject.AccountNumber_Balance += (accountNumber -> initialAccountBalance)
    // accounts should contain new accountnumber
    assert(accountOperationsObject.AccountNumber_Balance.contains(accountNumber))
    val result = accountOperationsObject.deleteAccount(accountNumber)
    assert(result == true)
    //after deleting it should remove that accounts entry
    assert(!accountOperationsObject.AccountNumber_Balance.contains(accountNumber))
  }

}



