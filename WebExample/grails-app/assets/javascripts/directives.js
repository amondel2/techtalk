angular.module('myApp.directives', []).directive('angulardir', function ($http,apiUrl) {
	return {
		restrict: 'A',
		link: function(scope, element, attrs) {
			var customMenu = function (node) { 
				var tree = $("#tree").jstree(true);
				  //Show a different label for renaming files and folders
				   var renameLabel = "Rename..";
				   var items = {
				      "create" : {
				          "label" : "Create New",
				          "action": function (obj) { 
			              node = tree.create_node(node);
			              tree.edit(node);
			          }
				      },
				      "rename" : {
				         "label" : renameLabel,  
				         "action" : function (obj) {  tree.edit(node); }
				      }
				      
				   };
				   
				   if((node.childType != 'job' || scope.orgCount > 1) && node.childType != 'organization' ) {
					   items["delete"]  = {
					         "label" : "Delete",
					         "action" : function (obj) { tree.delete_node(node); }
					      }
				   }
				   return items;
			};
			angular.element(element).jstree({
				'core' : {
					'data' : {
						'url' :  'company/jsTree',
						'data' : function (node) {
							if(node.id && node.id != '#') {
								return { 'id' : node.id, 'childType': node.childType};
							}
							
						}
					},
					'check_callback' : true,
					'themes' : {
						'responsive' : true
					}
				},
				'plugins' : ['state','dnd','contextmenu','wholerow', "html_data", "ui", "crrm"],
				 "contextmenu" : {
				        "items" : customMenu
				    }
			}).on('delete_node.jstree', function (e, data) {
				
				var controller = "company";
				if(data.node.parent) {
					var parentNode = $('#tree').jstree().get_node(data.node.parent);
					if(parentNode && parentNode.parent != null)
						controller = parentNode.childType;
				}
				$http.delete(apiUrl  + controller + "/" + data.node.id).success(function(myData, status, headers, config) {
					if(data.node.childType == "job") {
						scope.changeOrgCount(scope.orgCount - 1);
						if(!scope.$$phase) {
							//$digest or $apply
							scope.$digest();
						}
					}
				});
				
		}).on('rename_node.jstree', function (e, data) {
			var controller = "company";
			if(data.node.parent) {
				var parentNode = $('#tree').jstree().get_node(data.node.parent);
				if(parentNode && parentNode.parent != null)
					controller = parentNode.childType;
			}
			$http.put(apiUrl  + controller + "/" + data.node.id + "?name=" + data.text).success(function(myData, status, headers, config) {
				if(data.node.childType == "organization") {
					scope.companyName = data.text
					if(!scope.$$phase) {
						  //$digest or $apply
						scope.$digest();
					}
				}
		      
		    });
		}).on('create_node.jstree', function (e, data) {
//			var t = $('#tree').jstree('get_selected');
			var parentNode = $('#tree').jstree().get_node(data.node.parent);
			if(parentNode.childType == "organization") {
				scope.changeOrgCount(scope.orgCount + 1);
			}
			scope.$digest();
//		$.get('?operation=create_node', { 'id' : data.node.parent, 'position' : data.position, 'text' : data.node.text })
//			.done(function (d) {
//				data.instance.set_id(data.node, d.id);
//			})
//			.fail(function () {
//				data.instance.refresh();
//			});
	});
	}
	};
});