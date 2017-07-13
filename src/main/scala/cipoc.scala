/**
  * Created by hrishikesh.kshatriya on 7/13/2017.
  */
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.Row
import java.security.MessageDigest
import org.apache.spark.sql.Dataset
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
import scala.reflect.runtime.universe
import java.util.Calendar
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.functions.lit

object cipoc {
  def main(args: Array[String]): Unit = {


    val conf = new SparkConf().setAppName("CIPOC test")
    val sc = new SparkContext(conf)
    val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)


    val df = hiveContext.read.format("com.databricks.spark.csv").option("header", "true").load("/home/gitrepo/CSV_Files/Real_Estate1.xlsx")

    df.registerTempTable("csvload")

    hiveContext.sql("insert into cipoc.real_estate_trans select * from csvload")

  }

}
