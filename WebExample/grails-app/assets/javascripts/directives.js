angular.module('myApp.directives', []).directive('angulardir', function () {
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
				
//		$.get('?operation=delete_node', { 'id' : data.node.id })
//			.fail(function () {
//				data.instance.refresh();
//			});
				
				
				if(data.node.childType == "job") {
					scope.changeOrgCount(scope.orgCount - 1);
				}
				scope.$digest();
				
		}).on('rename_node.jstree', function (e, data) {
			if(data.node.childType == "organization") {
				scope.companyName = data.text
			}
			scope.$digest();
//		$.get('?operation=rename_node', { 'id' : data.node.id, 'text' : data.text })
//			.fail(function () {
//				data.instance.refresh();
//			});
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

