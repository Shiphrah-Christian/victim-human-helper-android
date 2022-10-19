<?php

require_once('config.php');

$query = "select * from state";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		while($row = mysqli_fetch_assoc($result)){
				$arrayData[] = $row;
		}
	    $data =array('Status'=>'True','Message'=>'State List','response'=>$arrayData);
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'State Unavailable');
		print(json_encode($data));
		exit;
}

?>