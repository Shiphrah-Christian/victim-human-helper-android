<?php

require('config.php');

$id = $_REQUEST['id'];
$status= $_REQUEST['status'];

$sql_query = "update Ambulance_user set status='".$status."' WHERE id='".$id."'";

$result = $db_con->query($sql_query);
						
	                    if($result)
						{
	                       $data =array('Status'=>'True','Message'=>'Update Successfully');
						   print(json_encode($data));
						   exit;
						}
						else
						{
						  $data = array('Status'=>'False','Message'=>'Update Unsuccessfully');	
						  print(json_encode($data));
						  exit;
						}

?>