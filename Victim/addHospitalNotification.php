<?php

require('config.php');

$hospitalId = $_REQUEST['hospitalId'];
$hospitalName = $_REQUEST['hospitalName'];
$userId= $_REQUEST['userId'];
$message= $_REQUEST['message'];

$query = "select * from Hospital_user WHERE id='".$hospitalId."'";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		$row = mysqli_fetch_assoc($result);
		$fcmId = $row['fcmid'];
		$data1 = array('body'=>$message);
		sendNotification($fcmId,$data1);
		
		$sql_query_not = "insert into hospitalNotification (`hospitalId`,`hospitalName`,`ambulanceId`,`message`,`fcmId`) VALUES('".$hospitalId."','".$hospitalName."','".$userId."','".$message."','".$fcmId."')";

            $result_not = $db_con->query($sql_query_not);
						
	                    if($result_not)
						{
	                       $data =array('Status'=>'True','Message'=>'Notification Send Successfully');
						   print(json_encode($data));
						   exit;
						}
						else
						{
						  $data = array('Status'=>'False','Message'=>'Notification Send Unsuccessfully');	
						  print(json_encode($data));
						  exit;
						}
}
else{
    $data = array('Status'=>'False','Message'=>'Invalid Area Name');	
						  print(json_encode($data));
						  exit;
}
?>