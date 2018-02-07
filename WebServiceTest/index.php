<?php

	define("DB_NAME","loco-test",true);
	define("DB_USER","temp_user",true);
	define("DB_PASS","temp_pass",true);
	define("DB_HOST","31.168.198.160",true);
	define("DB_QUERY1","SELECT * FROM `test`",true);
	define("DB_KEY","erty78912xx",true);
	
	try{
		
		
		if(!isset($_POST)||!isset($_POST['key'])||$_POST['key']!=DB_KEY)
			throw new Exception("invalid key.");
		
		
		//PHP by default connect to MySql with port ini_get("mysqli.default_port") = 3306 
		@$connection = new mysqli(DB_HOST,DB_USER,DB_PASS);
		$error_code = $connection->connect_errno;
		
		$connection->select_db(DB_NAME);
		
		if($error_code != 0)
			throw new Exception("no connection.");
		
		$result = $connection->query(DB_QUERY1,MYSQLI_STORE_RESULT);
		if(!$result)
			throw new Exception("query failed.");
		
		
			
		
	}catch(Exception $error){
		
		die("{\"status\":-1,\"reason\":\"".$error->getMessage()."\"}");
		
	}
	
	
	
	echo "{\"status\":$error_code,\"data\":";
	printContent($result);
	$result->free();
	$connection->close();
	echo "}";
	
	//service function
	
	function printContent($resultSet){
		
		
		//local function printRow for inner use only
		
		
		$printRow = function ($resultRow){
			
			static $hasFirst = true;
			
			$db_id    = $resultRow["db_id"];
			$from     = $resultRow["from"];
			$to       = $resultRow["to"];
			$date     = $resultRow["date"];
			$price    = $resultRow["price"];
			$currency = $resultRow["currency"];
			$favorite = ($resultRow["favorite"]==0?"false":"true");
			$image    = $resultRow["image"];
			
			
			$rowToPrint = "{\"db_id\":$db_id,
								\"from\":\"$from\",
								\"to\":\"$to\",
								\"date\":\"$date\",
								\"price\":$price,
								\"currency\":\"$currency\",
								\"favorite\":$favorite,
								\"image\":\"$image\"}";
			
			echo $hasFirst ? $rowToPrint : ','.$rowToPrint;
			
			$hasFirst = false;
		};
		
		
		echo '[';
		
		while($row = $resultSet->fetch_assoc()){
			$printRow($row);
			$hasFirst = false;
		}
		
		echo ']';
	}
	
	
	
?>