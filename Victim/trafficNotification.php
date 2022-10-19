<?php

require_once('config.php');

$trafficId = $_REQUEST['trafficId'];
$query = "select * from trafficNotification WHERE trafficId='".$trafficId."'";

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