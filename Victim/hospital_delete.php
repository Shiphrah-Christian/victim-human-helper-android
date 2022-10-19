<?php

require_once('config.php');

$id = $_REQUEST['id'];

$query = "delete from Hospital_user where id = '".$id."'";

$result = $db_con->query($query);

if($result){
	    $data =array('Status'=>'True','Message'=>'Profile delete Successfully');
		print(json_encode($data));
		exit;
}
else{
	$data =array('Status'=>'False','Message'=>'Profile delete Unsuccessfully');
		print(json_encode($data));
		exit;
}
?>