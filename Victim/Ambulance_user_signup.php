<?php

require('config.php');

$name = $_REQUEST['name'];
$contact = $_REQUEST['contact'];
$password= $_REQUEST['password'];
$fcmId= $_REQUEST['fcmId'];
$area= $_REQUEST['area'];
$city= $_REQUEST['city'];
$state= $_REQUEST['state'];
$email= $_REQUEST['email'];
$status= $_REQUEST['status'];


$sql_query = "insert into Ambulance_user (`name`,`contact`,`password`,`fcmId`,`area`,`city`,`state`,`email`,`status`) VALUES('".$name."','".$contact."','".$password."','".$fcmId."','".$area."','".$city."','".$state."','".$email."','".$status."')";

$result = $db_con->query($sql_query);
						
	                    if($result)
						{
	                       $data =array('Status'=>'True','Message'=>'Signup Successfully');
						   print(json_encode($data));
						   exit;
						}
						else
						{
						  $data = array('Status'=>'False','Message'=>'Signup Unsuccessfully');	
						  print(json_encode($data));
						  exit;
						}

?>