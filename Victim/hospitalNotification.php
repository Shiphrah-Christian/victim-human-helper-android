<?php

require_once('config.php');

$hospitalId = $_REQUEST['hospitalId'];
$query = "select * from hospitalNotification WHERE hospitalId='".$hospitalId."'";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		while($row = mysqli_fetch_assoc($result)){
				$arrayData[] = $row;
		}
	    $data =array('Status'=>'True','Message'=>'Notification List','response'=>$arrayData);
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'Notification Unavailable');
		print(json_encode($data));
		exit;
}

?>