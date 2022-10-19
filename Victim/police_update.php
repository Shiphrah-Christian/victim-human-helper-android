<?php

require_once('config.php');

$id = $_REQUEST['id'];
$name=$_REQUEST['name'];
$password = $_REQUEST['password'];
$contact = $_REQUEST['contact'];

$query = "update police_user set name='".$name."',password='".$password."',contact = '".$contact."' WHERE id='".$id."'";

$result = $db_con->query($query);

if($result){
	    $data =array('Status'=>'True','Message'=>'Profile Update Successfully');
		print(json_encode($data));
		exit;
}
else{
	$data =array('Status'=>'False','Message'=>'Profile Update Unsuccessfully');
		print(json_encode($data));
		exit;
}
?>