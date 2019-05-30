function RentManagerLogout() {
		$.post("/RentManagerCon/RentManagerLogout.do", function(data){
			if(data.status=='200'){
				$(location).attr('href', 'rent-manager-login.html');
				alert("注销成功");
				}
		    });
}