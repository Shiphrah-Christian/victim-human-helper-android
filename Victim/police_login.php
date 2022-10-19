<?php

require_once('config.php');

$contact = $_REQUEST['contact'];
$password= $_REQUEST['password'];


$query = "select * from police_user WHERE contact = '".$contact."' AND password='".$password."'";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		while($row = mysqli_fetch_assoc($result)){
				$arrayData[] = $row;
		}
	    $data =array('Status'=>'True','Message'=>'Login Successfully','response'=>$arrayData);
		print(json_encode($data));
		exit;
}
else{
	    $data =array('Status'=>'False','Message'=>'Login Unsuccessfully');
		print(json_encode($data));
		exit;
}

?>