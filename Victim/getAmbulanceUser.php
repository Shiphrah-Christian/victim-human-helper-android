<?php

require_once('config.php');

$status = $_REQUEST['status'];

if($status=='Pending'){
    $query = "select * from Ambulance_user WHERE status ='Pending'";
}
else{
    $query = "select * from Ambulance_user WHERE status !='Pending'";
}

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		while($row = mysqli_fetch_assoc($result)){
				$arrayData[] = $row;
		}
	    $data =array('Status'=>'True','Message'=>'Ambulance Lists','response'=>$arrayData);
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'Ambulance List Unavailable');
		print(json_encode($data));
		exit;
}

?>