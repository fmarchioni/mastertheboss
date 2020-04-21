
<html>
<head>
 <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <link rel="stylesheet" type="text/css" href="stylesheet.css" media="screen" />


<script type = "text/javascript">
	angular.module('app', [])
	.controller('ctrl', function ($scope, $http) {
		$scope.files = [];

		$scope.loadData = function () {
			$http({
				method: 'GET',
				url: '/rest-file-manager/rest/file/list'
			}).then(function successCallback(response) {
				$scope.files = response.data;
			}, function errorCallback(response) {
				console.log(response.statusText);
			});
		}

		$scope.downloadFile = function (filename) {

			$http({
				method: 'GET',
				url: '/rest-file-manager/rest/file/download',
				params: {
					file: filename
				},
				responseType: 'arraybuffer'

			}).success(function (data, status, headers) {

				headers = headers();

				//var filename = headers['Content-Disposition'];

				var contentType = headers['content-type'];
				var linkElement = document.createElement('a');

				try {

					var blob = new Blob([data], {
						type: contentType
					});

					var url = window.URL.createObjectURL(blob);

					linkElement.setAttribute('href', url);
					linkElement.setAttribute("download", filename);

					var clickEvent = new MouseEvent("click", {

						"view": window,
						"bubbles": true,
						"cancelable": false

					});

					linkElement.dispatchEvent(clickEvent);

				} catch (ex) {
					console.log(ex);
				}

			}).error(function (data) {
				console.log(data);
			});

		};


		//call loadData when controller initialized
		$scope.loadData();
	})
</script>



<title>REST Download demo with AngularJS</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body ng-app='app' ng-controller='ctrl'>
    <h1>REST Upload/Download demo using AngularJS</h2>
	<h2>Upload file</h2>
	<form method="post" action="rest/file/upload"
		enctype="multipart/form-data">
		<input type="hidden" name="action" value="upload" /> <label>Load
			your file:</label> <input type="file" name="attachment" /> <br /> <input
			type="submit" value="Upload file" />
	</form>

	<h2>Download file</h2>

        <div class="divTable blueTable">
                <div class="divTableHeading">
                    <div  class="divTableHead">File Name</div>
                    <div  class="divTableHead">Action</div>
                </div>
                <div class="divTableRow" ng-repeat="file in files">
                    <div class="divTableCell">{{ file }}</div>
                    <div class="divTableCell"><a ng-click="downloadFile( file )" class="myButton">Download</a> </div>
                </div>
            </div>
</body>
</html>

