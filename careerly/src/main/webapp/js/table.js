var Table = (function(e) {
    var tableId = '';
    var isCheckAll = false;

    var _bindTr = function(odd, even, hover, click) {
        $('#' + tableId).find('tr').each(function() {
            var jqEle = $(this);
            var color = jqEle.get(0).sectionRowIndex % 2 == 0 ? odd : even;
            jqEle.css('backgroundColor', color);
            jqEle.click(function() {
                var ele = $(this);
                if(ele.attr('sel') != 1) {
                    ele.attr('sel', 1);
                    ele.css('backgroundColor', click);
                    ele.find('input.chk').each(function() {
                    	if(!$(this).attr('disabled') && $(this).parent().prop('tagName') !== 'TH') {
                    		$(this).prop('checked', true);
                    	}
                    });
                } else {
                    ele.attr('sel', 0);
                    ele.css('backgroundColor', color);
                    ele.find('input.chk').each(function() {
                    	if(!$(this).attr('disabled') && $(this).parent().prop('tagName') !== 'TH') {
                    		$(this).prop('checked', false);
                    	}
                    });
                }
            }).mouseover(function() {
                var ele = $(this);
                if(ele.attr('sel') != 1) {
                    ele.css('backgroundColor', hover);
                }
            }).mouseout(function() {
                var ele = $(this);
                if(ele.attr('sel') != 1) {
                    ele.css('backgroundColor', color);
                }
            });
        });
    };

    var checkNull = function(obj) {
    	return typeof obj === 'undefined' || obj == null;
    };


    var init = function(id, odd, even, hover, click) {
        tableId = id;
        var odd = checkNull(odd) ? '#ecf5fe' : odd;
        var even = checkNull(even) ? '#ffffff' : even;
        var hover = checkNull(hover) ? '#c7e5ab' : hover;
        var click = checkNull(click) ? '#e0e5e9' : click;
        _bindTr(odd, even, hover, click);
    };


    var checkAll = function() {
        isCheckAll = !isCheckAll;
        $('#' + tableId).find('input.chk').each(function() {
        	if(!$(this).attr('disabled')) {
        		$(this).prop('checked', isCheckAll);
        	}
        });
        var checkAllElm = $('#' + tableId).parent().find('.table_bot_comm').find('a:first');

        if(checkAllElm) {
            var text = checkAllElm.html();
            if(text == '全选' || text == '取消全选') {
                checkAllElm.html(text == '全选' ? '取消全选' : '全选');
            }
        };

        if(typeof allcheckDisable !== 'undefined') {
        	allcheckDisable();
        }
    };

    var bindDblClick = function(fun) {
        $('#' + tableId).find('tr').dblclick(function() {
        	if($(this).find('th').length <= 0) {
                fun.call(e,this);
        	}
        });
    };

    var bindChk = function(chkFun) {
    	$('#' + tableId).find('input.chk').change(function() {
    		chkFun.call(this, this);
    	});
    	$('#' + tableId).find('tr').click(function() {
    		chkFun.call(this, this);
    	});
    };
    
    var isChecked = function() {
    	return isCheckAll;
    };

    return {
        init: init,
        checkAll: checkAll,
        bindDblClick: bindDblClick,
        bindChk: bindChk,
        isChecked: isChecked
    };
}).call(this);

/*
 *  用于操作选中行后 相应功能的有效或失效切换
 *
 *  Check.set(ids, enableFlag);
 *  enableFlag true - 有效 false - 失效
 *
 *  Check.enable(ids); //ids对应的操作有效
 *  Check.disable(ids); //ids对应的操作失效
 *
 *  对于全选状态时
 *  操作前：Check.setCatch();
 *  操作后：Check.clearCatch();
 */
var Check = (function() {
    var _catchObj = {};
    var _catchFlag = false;

    var _getCanSet = function(id, enableFlag) {
        var canSet = true;
        if(!_catchFlag) return canSet;
        if(typeof _catchObj[idAry[i]] !== 'undefined') {
            canSet = _catchObj[idAry[i]] && enableFlag;
        } else {
            _catchObj[idAry[i]] = enableFlag;
        }
        return canSet;
    };

    var _enable = function(ids, enableFlag) {
        var idAry = ids.split(',');
        for(var i = 0; i < idAry.length; i++) {
        	var id = $.trim(idAry[i]);
            var elm = $('#' + id);
            var elmGray = $('#' + id + '_gray');
            if(elm.length > 0 && elmGray.length > 0 && id !== '') {
                var canSet = _getCanSet(id, enableFlag);
                if(!canSet) continue;
                if(enableFlag) {
                    elm.show();
                    elmGray.hide();
                } else {
                    elm.hide();
                    elmGray.show();
                }
            }
        }
    };

    //设置ids对应的内容有效
    var enable = function(ids) {
        _enable(ids, true);
        return this;
    };

    //设置ids对应的内容无效
    var disable = function(ids) {
        _enable(ids, false);
        return this;
    };

    //根据ids设置是否有效 enableFlag：true-有效 false-无效
    var set = function(ids, enableFlag) {
        return _enable(ids, enableFlag);
    };

    //打开catch开关
    var setCatch = function() {
        _catchFlag = true;
        _catchObj = {};
    };

    //清楚catch
    var clearCatch = function() {
        _catchObj = {};
        _catchFlag = false;
    };

    return {
        enable: enable,
        disable: disable,
        set: set,
        setCatch: setCatch,
        clearCatch: clearCatch
    };
}).call(this);