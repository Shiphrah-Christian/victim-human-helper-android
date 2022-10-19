<?php

require_once('config.php');

$query = "select * from Hospital_user";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		while($row = mysqli_fetch_assoc($result)){
				$arrayData[] = $row;
		}
	    $data =array('Status'=>'True','Message'=>'Hospital List','response'=>$arrayData);
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'Hospital Unavailable');
		print(json_encode($data));
		exit;
}

?>