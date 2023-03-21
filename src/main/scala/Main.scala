package com.knoldus

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
    val accountOperations= new AccountOperations
    accountOperations.createAccount(1253.56)
    accountOperations.createAccount(1254.56)
    accountOperations.createAccount(1255.56)
    println(accountOperations.createAccount(54))
    val accountSheet=accountOperations.AccountNumber_Balance
    accountSheet.foreach(account=>println(accountOperations.fetchAccountBalance(account._1)))
    val accountNumbers=accountSheet.keys.toList
    val transactionlist = List(
      Transactions(1,accountNumbers.head,"Credit",200.43) ,
      Transactions(2,accountNumbers(1),"Credit",200.43) ,
      Transactions(3,accountNumbers(1),"Credit",200.43) ,
      Transactions(4,accountNumbers(3),"Credit",200.43) ,
    )
    println(accountOperations.updateBalance(transactionlist))
  }
}