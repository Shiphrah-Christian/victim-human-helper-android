<?php

require_once('config.php');

$stateId = $_REQUEST['stateId'];
$query = "select * from city WHERE stateId='".$stateId."'";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		while($row = mysqli_fetch_assoc($result)){
				$arrayData[] = $row;
		}
	    $data =array('Status'=>'True','Message'=>'City List','response'=>$arrayData);
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'City Unavailable');
		print(json_encode($data));
		exit;
}

?>