<?php

require('config.php');

$areaId = $_REQUEST['areaId'];
$areaName = $_REQUEST['areaName'];
$userId= $_REQUEST['userId'];
$message= $_REQUEST['message'];

$query = "select * from police_user WHERE area='".$areaName."'";

$result = $db_con->query($query);

$rowCount = $result->num_rows;

if($rowCount>0){

		$row = mysqli_fetch_assoc($result);
		$trafficId = $row['id'];
		$fcmId = $row['fcmId'];
		$data1 = array('body'=>$message);
		sendNotification($fcmId,$data1);
		
		$sql_query_not = "insert into trafficNotification (`areaId`,`areaName`,`userId`,`message`,`trafficId`,`fcmId`) VALUES('".$areaId."','".$areaName."','".$userId."','".$message."','".$trafficId."','".$fcmId."')";

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