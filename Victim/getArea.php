<?php

require_once('config.php');

$cityId = $_REQUEST['cityId'];
$query = "select * from area WHERE cityId='".$cityId."'";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		while($row = mysqli_fetch_assoc($result)){
				$arrayData[] = $row;
		}
	    $data =array('Status'=>'True','Message'=>'Area List','response'=>$arrayData);
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'Area Unavailable');
		print(json_encode($data));
		exit;
}

?>