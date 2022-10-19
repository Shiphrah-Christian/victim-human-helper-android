<?php

require_once('config.php');

$id = $_REQUEST['id'];
$name=$_REQUEST['name'];
$password = $_REQUEST['password'];
$contact = $_REQUEST['contact'];
$area = $_REQUEST['area'];
$city = $_REQUEST['city'];
$state = $_REQUEST['state'];
$email = $_REQUEST['email'];

$query = "update Ambulance_user set name='".$name."',email='".$email."',password='".$password."',contact = '".$contact."',area = '".$area."',city = '".$city."',state = '".$state."' WHERE id='".$id."'";

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