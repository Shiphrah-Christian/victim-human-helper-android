<?php

require('config.php');

$name = $_REQUEST['name'];
$contact = $_REQUEST['contact'];
$password= $_REQUEST['password'];
$area= $_REQUEST['area'];
$city= $_REQUEST['city'];
$state= $_REQUEST['state'];
$fcmId= $_REQUEST['fcmId'];

$sql_query = "insert into Hospital_user (`name`,`contact`,`password`,`area`,`city`,`state`,`fcmId`) VALUES('".$name."','".$contact."','".$password."','".$area."','".$city."','".$state."','".$fcmId."')";

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