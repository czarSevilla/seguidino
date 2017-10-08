

$(function(){
	$('#header').load("/menu");
	$('#issueForm').on("submit", function(event){
		event.preventDefault();
		SEGUIDINO.addIssue();
	});
	$('#avatar').on('fileuploaded', function(event, data, previewId, index){
		if(data.jqXHR.responseJSON.result == 'OK') {
			var url = data.jqXHR.responseJSON.url;
			$('.logo-action img').attr("src", url);
			var idProject = data.extra.idProject;
			var row = $('#proj-item-' + idProject);
			$('.project-avatar', row).attr('src', url);
			var editLogoForm = $('#logoModal');
			$('#header').load("/menu");
			//editLogoForm.modal('hide');
			//var editForm = $('#editModal');
			//editForm.modal('show');
		}
	});
	
	$('.editable-icon').click(function(evt){
		var editable = $(evt.target).closest('.editable');
		$('.label-field', editable).hide();
		$('.editable-field', editable).show();
		$('.editable-field > input', editable).focus(function() {
 			   $(this).select();
 		});
		$('.editable-field > input', editable).focus();
		$('.icon-combo', editable).hide();
		$('.editable-field > select', editable).click();
		$(editable).addClass('onEdition');
	});
	
	$(document).click(function (event) {            
	    if ($('.onEdition').size() > 0) {
	    	if (!$(event.target).is('.onEdition, .onEdition *')) {
	    		SEGUIDINO.resetEditable();	
	    	}        	    	
	    }
	});
	
	$(document).keyup(function(e) {
   	     if (e.keyCode == 27) { // escape key maps to keycode `27`
   	        if ($('.onEdition').size() > 0) {
   	        	resetEditable();
   	        }
   	    }
   	});
});

SEGUIDINO = {};

SEGUIDINO.jsonToOptions = function(data) {
	var html = "";
	$.each(data, function(idx){
		option = data[idx];
		html += "<option value=\"" + option.key + "\" ";
		if (option.selected) {
			html += "selected=\"selected\" ";
		}
		html += ">";
		html += option.value;
		html += "</option>";
	});
	return html;
};

SEGUIDINO.updateComponents = function(idProject) {
	$.ajax({
		url:'/api/projects/' + idProject + '/components',
		cache:false,
		dataType:'json',
		success:function(data) {
			html = SEGUIDINO.jsonToOptions(data);
			$('#component-select').empty();
			$('#component-select').append(html);
			$('#component-select').selectpicker({size:4});
		}
	});
};

SEGUIDINO.updateAssignees = function(idProject) {
	$.ajax({
		url:'/api/projects/' + idProject + '/assignees',
		cache:false,
		dataType:'json',
		success:function(data) {
			html = SEGUIDINO.jsonToOptions(data);
			$('#assignee-select').empty();
			$('#assignee-select').append(html);
			$('#assignee-select').selectpicker('refresh');
		}
	});
};

SEGUIDINO.updateProjectChildren = function(idProject) {
	SEGUIDINO.updateComponents(idProject);
	SEGUIDINO.updateAssignees(idProject);
};

SEGUIDINO.addIssue = function() {
	$.ajax({
		url:'/issues/add',
		cache:false,
		method:'POST',
		data:$('#issueForm').serialize(),
		dataType:'json',
		success:function(data){
			if (data.result == 'OK') {				
				var url = "/issues/show/" + data.keyIssue;
				$('#showForm').attr('action', url).submit();
			}
			
		}
	});
};

SEGUIDINO.showIssueForm = function() {	
	$('#issueDivForm').dialog({
      autoOpen: false,
      modal: true,
      height: 600,
      width: 500,
      buttons: {
        "Create": SEGUIDINO.addIssue,
         Cancel: function() {
          $('#issueDivForm').dialog("close");
        }
      },
      close: function() {
    	$('form', '#issueDivForm').each(function(idx){
  		  this.reset();
  	  }); 
      }
    });
	
	$('.ui-dialog-buttonset > button').addClass('btn btn-default');
	$('.ui-dialog-buttonset > button:first').addClass('btn btn-primary');
	
	$.ajax({
		url:'/api/projects/select',
		cache:false,
		dataType:'json',
		success:function(data) {
			var html = SEGUIDINO.jsonToOptions(data);			
			$('#project-select').empty();
			$('#project-select').append(html);
			$('#project-select').selectpicker('refresh');
			$.each(data, function(idx) {
				if (data[idx].selected) {
					SEGUIDINO.updateProjectChildren(data[idx].key);
				}
			});
		}
	});
	
	$.ajax({
		url:'/api/issues/types',
		cache:false,
		dataType:'json',
		success:function(data){
			var html = SEGUIDINO.jsonToOptions(data);
			$('#type-select').empty();
			$('#type-select').append(html);
			$('#type-select').selectpicker('refresh');
		}
	});
	
	$.ajax({
		url:'/api/issues/priorities',
		cache:false,
		dataType:'json',
		success:function(data){
			var html = SEGUIDINO.jsonToOptions(data);
			$('#priority-select').empty();
			$('#priority-select').append(html);
			$('#priority-select').selectpicker('refresh');
		}
	});
	
	$.ajax({
		url:'/api/users/principal',
		cache:false,
		dataType:'json',
		success:function(data) {
			$('#author-name').val(data.value);
			$('#author-id').val(data.key);
		}
	});
	
	$('#issueDivForm').dialog('open');
};

SEGUIDINO.startProgress = function(idIssue) {
	$.ajax({
		url:'/api/issues/' + idIssue + '/startProgress',
		cache: false,
		dataType:'json',
		success:function(data) {
			if (data.result == 'OK') {
				var html = '<span class="glyphicon ' + data.issueStatus.icon + '"></span>';
				html += '&nbsp;<span>' + data.issueStatus.name + '</span>';
				$('#div-status').empty();
				$('#div-status').append(html);
				$('#btnStartProgress').hide();
				$('#btnStopProgress').show();
			}
		}
	});
};

SEGUIDINO.stopProgress = function(idIssue) {
	$.ajax({
		url:'/api/issues/' + idIssue + '/stopProgress',
		cache: false,
		dataType:'json',
		success:function(data) {
			if (data.result == 'OK') {
				var html = '<span class="glyphicon ' + data.issueStatus.icon + '"></span>';
				html += '&nbsp;<span>' + data.issueStatus.name + '</span>';
				$('#div-status').empty();
				$('#div-status').append(html);
				$('#btnStartProgress').show();
				$('#btnStopProgress').hide();
			}
		}
	});
};

SEGUIDINO.showResolveForm = function(idIssue, idProject) {	
	$.ajax({
		url:'/api/issues/resolutions',
		cache:false,
		dataType:'json',
		success:function(data){
			var html = SEGUIDINO.jsonToOptions(data);
			$('#resolution-select').empty();
			$('#resolution-select').append(html);
			$('#resolution-select').selectpicker('refresh');
		}
	});
	
	$.ajax({
		url:'/api/projects/' + idProject + '/assignees',
		cache:false,
		dataType:'json',
		success:function(data) {
			html = SEGUIDINO.jsonToOptions(data);
			$('#resolve-assignee-select').empty();
			$('#resolve-assignee-select').append(html);
			$('#resolve-assignee-select').selectpicker('refresh');
		}
	});
	
	$('#resolveDivForm').dialog({
	      autoOpen: false,
	      height: 400,
	      width: 350,
	      modal: true,
	      title: 'Resolve Issue',
	      buttons: {
	        "Resolve": SEGUIDINO.resolveIssue,
	         Cancel: function() {
	          $('#resolveDivForm').dialog("close");
	        }
	      },
	      close: function() {
	    	  $('form', '#resolveDivForm').each(function(idx){
	    		  this.reset();
	    	  });            	        
	      }
	    });    
	
	$('.ui-dialog-buttonset > button').addClass('btn btn-default');
	$('.ui-dialog-buttonset > button:first').addClass('btn btn-primary');
	
	$('#resolveDivForm').dialog('open');
};

SEGUIDINO.resolveIssue = function() {
	$.ajax({
		url:'/issues/resolve',
		cache:false,
		method:'POST',
		data:$('#resolveForm').serialize(),
		dataType:'json',
		success:function(data){
			if (data.result == 'OK') {				
				var url = "/issues/show/" + data.keyIssue;
				$('#showForm').attr('action', url).submit();
			}
			
		}
	});
};

SEGUIDINO.showReopenForm = function(idIssue, idProject) {
	$('#reopenDivForm').dialog({
	      autoOpen: false,
	      height: 400,
	      width: 350,
	      modal: true,
	      buttons: {
	        "Reopen": SEGUIDINO.reopenIssue,
	         Cancel: function() {
	          $('#reopenDivForm').dialog("close");
	        }
	      },
	      close: function() {
	    	  $('form', '#reopenDivForm').each(function(idx){
	    		  this.reset();
	    	  });            	        
	      }
	    }); 
	
	$('.ui-dialog-buttonset > button').addClass('btn btn-default');
	$('.ui-dialog-buttonset > button:first').addClass('btn btn-primary');
	
	$.ajax({
		url:'/api/projects/' + idProject + '/assignees',
		cache:false,
		dataType:'json',
		success:function(data) {
			html = SEGUIDINO.jsonToOptions(data);
			$('#reopen-assignee-select').empty();
			$('#reopen-assignee-select').append(html);
			$('#reopen-assignee-select').selectpicker('refresh');
		}
	});
	
	$('#reopenDivForm').dialog('open');
};

SEGUIDINO.reopenIssue = function() {
	$.ajax({
		url:'/issues/reopen',
		cache:false,
		method:'POST',
		data:$('#reopenForm').serialize(),
		dataType:'json',
		success:function(data){
			if (data.result == 'OK') {				
				var url = "/issues/show/" + data.keyIssue;
				$('#showForm').attr('action', url).submit();
			}
		}
	});
};

SEGUIDINO.showProjectTab = function(tabName, keyProject) {
	$("#contentTab").load('/projects/' + keyProject + '/' + tabName);
};

SEGUIDINO.showCloseForm = function(idIssue, idProject) {
	$('#resolveDivForm').dialog({
	      autoOpen: false,
	      height: 400,
	      width: 350,
	      modal: true,
	      title: 'Close Issue',
	      buttons: {
	        "Close": SEGUIDINO.closeIssue,
	         Cancel: function() {
	          $('#resolveDivForm').dialog("close");
	        }
	      },
	      close: function() {
	    	  $('form', '#resolveDivForm').each(function(idx){
	    		  this.reset();
	    	  });            	        
	      }
	    });     
	
	$('.ui-dialog-buttonset > button').addClass('btn btn-default');
	$('.ui-dialog-buttonset > button:first').addClass('btn btn-primary');
	
	$.ajax({
		url:'/api/issues/resolutions',
		cache:false,
		dataType:'json',
		success:function(data){
			var html = SEGUIDINO.jsonToOptions(data);
			$('#resolution-select').empty();
			$('#resolution-select').append(html);
			$('#resolution-select').selectpicker('refresh');
		}
	});
	
	$.ajax({
		url:'/api/projects/' + idProject + '/assignees',
		cache:false,
		dataType:'json',
		success:function(data) {
			html = SEGUIDINO.jsonToOptions(data);
			$('#resolve-assignee-select').empty();
			$('#resolve-assignee-select').append(html);
			$('#resolve-assignee-select').selectpicker('refresh');
		}
	});
	
	$('#resolveDivForm').dialog('open');
};

SEGUIDINO.closeIssue = function() {
	$.ajax({
		url:'/issues/close',
		cache:false,
		method:'POST',
		data:$('#resolveForm').serialize(),
		dataType:'json',
		success:function(data){
			if (data.result == 'OK') {				
				var url = "/issues/show/" + data.keyIssue;
				$('#showForm').attr('action', url).submit();
			}
			
		}
	});
};

SEGUIDINO.showAddComment = function(idIssue) {
	SEGUIDINO.showComments(idIssue);
	$('#divAddComment').show();
	$('#idIssue', '#divAddComment').val(idIssue);
	$('#txtComment').focus();
};

SEGUIDINO.cancelAddComment = function() {
	$('#txtComment').val('');
	$('#divAddComment').hide();
};

SEGUIDINO.addComment = function() {
	$.ajax({
		url:'/issues/addComment',
		cache:false,
		method:'POST',
		data:$('#formAddComment').serialize(),
		dataType:'html',
		success:function(data){
			$('#listComments').append(data);
			SEGUIDINO.cancelAddComment();
		}
	});
};

SEGUIDINO.showComments = function(idIssue) {
	$('.tab-issue').removeClass("active");
	$('.tab-issue-detail').hide();
	$('#tabComments').addClass("active");
	$('#listComments').load('/issues/show/' + idIssue + '/comments');
	$('#listComments').show();
}

SEGUIDINO.showHistory = function(idIssue) {
	$('.tab-issue').removeClass("active");
	$('.tab-issue-detail').hide();
	$('#tabHistory').addClass("active");
	$('#histories').load('/issues/show/' + idIssue + '/history');
	$('#histories').show();
};

SEGUIDINO.showActivity = function(idIssue) {
	$('.tab-issue').removeClass("active");
	$('.tab-issue-detail').hide();
	$('#tabActivity').addClass("active");
	$('#activities').load('/issues/show/' + idIssue + '/activities');
	$('#activities').show();
};

SEGUIDINO.showTransitions = function(idIssue) {
	$('.tab-issue').removeClass("active");
	$('.tab-issue-detail').hide();
	$('#tabTransitions').addClass("active");
	$('#transitions').load('/issues/show/' + idIssue + '/transitions');
	$('#transitions').show();
};

SEGUIDINO.showIssue = function(keyIssue) {
	var url = "/issues/show/" + keyIssue;
	$('#showForm').attr('action', url).submit();
};

SEGUIDINO.showEditableIcon = function(wrapper) {
	$('.editable-icon', $(wrapper)).show();
};

SEGUIDINO.hideEditableIcon = function(wrapper) {
	$('.editable-icon', $(wrapper)).hide();
};

SEGUIDINO.showEditProject = function(idProject) {
	
	console.log("showEditPrject(" + idProject + ")");
	var projRow = $('#proj-item-' + idProject);
	var name = $('.proj-item-name', projRow).text();
	var avatar = $('.project-avatar', projRow).attr('src');
	var url = $('input[name=url]', projRow).val();
	var desc = $('input[name=description]', projRow).val();
	
	var editForm = $('#editModal');
	$('input[name=name]', editForm).val(name);
	$('.logo img', editForm).attr("src", avatar);
	$('textarea[name=description]', editForm).text(desc);
	$('.logo-action', editForm).attr("onclick", "SEGUIDINO.showLogoEdit(" + idProject + ");");
	editForm.modal('show');
	
};

SEGUIDINO.showLogoEdit = function(idProject) {
	console.log("showLogoEdit(" + idProject + ")");
	var editForm = $('#editModal');
	editForm.modal('hide');
	var editLogoForm = $('#logoModal');
	var csrf = $('input[name=_csrf]').val();
	$('#avatar').fileinput({
        type: 'POST',
        cache: false,
        allowedFileExtensions: ['jpg', 'png', 'gif', 'svg'],
        allowedFileTypes: ['image'],
        maxFileSize: 2000,
        uploadUrl: '/admin/projects/logo',
        maxFileCount: 1,
        enctype: 'multipart/form-data',
        overwriteInitial: true,    
        uploadExtraData :{idProject:idProject,_csrf:csrf}
    });        		
	editLogoForm.modal('show');
	editLogoForm.on('hidden.bs.modal', function(e) {
		editForm.modal('show');
	});	
};


SEGUIDINO.resetEditable = function() {
	$('.editable').each(function(){
		if ($(this).hasClass('onEdition')) {
			$('.label-field', this).show();
    		$('.editable-field', this).hide();
    		$('.icon-combo', this).show();
    		$(this).removeClass('onEdition');
		}            		
	});
};

SEGUIDINO.editarCampo = function(idEditable, idIssue) {
	var editable = $("span[id=" + idEditable + "]");
	var nombreCampo = $('.form-control', editable).attr('name');
	var valorCampo = $('.form-control', editable).val();
	var tokenValue = $('#sec-token').val();
	$.ajax({
		url:'/issues/' + idIssue + '/changeField',
		cache:false,
		method:'POST',
		data:{
			fieldName:nombreCampo,
			fieldValue:valorCampo,
			_csrf:tokenValue
		},
		dataType:'json',
		success:function(data){
			if (data.result == 'OK') {
				$('.label-field', editable).text(valorCampo);
				SEGUIDINO.resetEditable();
			}			
		}
	});
};

SEGUIDINO.editarCombo = function(idEditable, idIssue) {
	var editable = $("span[id=" + idEditable + "]");
	console.log('editable: ' + $(editable).attr('class'));
	var nombreCampo = $('select', editable).attr('data-name');
	console.log('nombreCampo: ' + nombreCampo);
	var valorCampo = $('select option:selected', editable).val();
	var textoCampo = $('select option:selected', editable).text();
	var iconoAnterior = $('.icon-combo', editable).attr('data-icon');
	var iconoActual = $('select option:selected', editable).attr('data-icon');
	var textoActual = $('.label-field', editable).text();
	var tokenValue = $('#sec-token').val();
	if (textoActual != textoCampo) {
		$.ajax({
			url:'/issues/' + idIssue + '/changeField',
			cache:false,
			method:'POST',
			data:{
				fieldName:nombreCampo,
				fieldValue:valorCampo,
				_csrf:tokenValue
			},
			dataType:'json',
			success:function(data){
				if (data.result == 'OK') {
					$('.label-field', editable).text(textoCampo);
					$('.icon-combo', editable).removeClass(iconoAnterior);
					$('.icon-combo', editable).addClass(iconoActual);
					$('.icon-combo', editable).attr('data-icon', iconoActual);
					SEGUIDINO.resetEditable();
					$('#header').load("/menu");
				}			
			}
		});
		
	}
	SEGUIDINO.resetEditable();
};