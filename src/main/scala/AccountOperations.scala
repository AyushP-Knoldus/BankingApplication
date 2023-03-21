package com.knoldus

import scala.collection.mutable.Map
import scala.util.Random

class AccountOperations {
  val AccountNumber_Balance: Map[Long, Double]=Map()

  def createAccount(openingBalance: Double): Map[Long, Double] = {
    val accountNumber = generateAccountNumber(Random.nextLong(10000L))
    AccountNumber_Balance(accountNumber)= openingBalance
    AccountNumber_Balance
  }
  def listAccounts(): Map[Long, Double] = {
    AccountNumber_Balance
  }
  def fetchAccountBalance(accountNumber: Long): Double ={
   val accountBalance= AccountNumber_Balance.getOrElse(accountNumber,-1.0)
    accountBalance
  }
  def updateBalance(transactions: List[Transactions]): Map[Long, Double] = {
 transactions.foreach{transaction =>
   transaction.transactionType match{
     case "Credit" => AccountNumber_Balance.update(transaction.accountNumber,transaction.amount + fetchAccountBalance(transaction.accountNumber))
     case "Debit" => AccountNumber_Balance.update(transaction.accountNumber,fetchAccountBalance(transaction.accountNumber) - transaction.amount)
   }
 }
    AccountNumber_Balance
  }
  def deleteAccount(accountNumber: Long): Boolean = {
    if (AccountNumber_Balance.contains(accountNumber)) {
      AccountNumber_Balance -= accountNumber
      true
    }
    else {
      false
    }
  }

  def generateAccountNumber(accountNumber: Long): Long = {
    if (AccountNumber_Balance.contains(accountNumber)) {
      generateAccountNumber(Random.nextLong(1000000L))
    }
    else {
      accountNumber
    }
  }

}
