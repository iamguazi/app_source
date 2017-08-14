// JavaScript Document
(function( window, undefined ) {
	// ****************************************************************************************//
	// window 扩展
	if(!window.isFunction){
		window.isFunction = function(){
			// Performance optimization: Lazy Function Definition
			return "object"  === typeof document.getElementById ?
				   isFunction = function(fn){
						try {
							return /^\s*\bfunction\b/.test("" + fn);
						} catch (x) {
							return false;
						}
				   }:
				   isFunction = function(fn){
					   return "[object Function]" === Object.prototype.toString.call(fn);
				   };
		}
	}	  
	if(!window.isArray){
		window.isArray = function(o){
			return typeof o === "object" && Object.prototype.toString.call(o) == "[object Array]";
		}
	}
	if (!window.stopPropagation) {
		window.stopPropagation = function(e) {
			e = e || window.event;
			if (e.stopPropagation) { // W3C阻止冒泡方法
				e.stopPropagation();
			} else {
				e.cancelBubble = true; // IE阻止冒泡方法
			}
		}
	}
	if(!window.defineProperty){
		window.defineProperty = function(){
			return 	Object.defineProperty ?
					window.defineProperty = Object.defineProperty :
					window.defineProperty = function(prototype, methodName, def, type){
						prototype[methodName] = def.value;
					}
		}
	}
	
	// ****************************************************************************************//
	// Array 扩展
	Array.prototype.S = String.fromCharCode(2);
	if(!Array.prototype.add){
		// 将一个新数组添加原数组的末尾，并返回添加后的原数组，此操作会强制破坏原数组的完整性
		Array.prototype.add = function(secArray){
			Array.prototype.push.apply(this, secArray);
			return this;
		}
	}
	if(!Array.prototype.firstIndexOf){
		// 查找首次匹配val值的数组的索引，beginPosition为可选参数，-1表示末尾(以此类推)
		// 找不到匹配的值则返回-1
		Array.prototype.firstIndexOf = function(val, beginPosition){
			var length = 0
				,i = 0;
			if(this === null || this === undefined)	return -1;
			length = this.length;
			if(!beginPosition)	beginPosition = 0;
			else if(Math.abs(beginPosition) > length)	return -1;
			else if(beginPosition <= -1)
					beginPosition = (length + beginPosition) % length;
			for(i = beginPosition; i < length; i++){
				if(this[i] == val)	return i;
			}
			return -1;
			
		}
	}
	if(!Array.prototype.lastIndexOf){
		// 查找匹配val值的最后一个元素的索引，beginPosition为可选参数，-1表示末尾(以此类推)
		// 找不到匹配的值则返回-1
		Array.prototype.lastIndexOf = function(val, beginPosition){
			var i = 0
				,length = 0;
			if(this === null || this === undefined)	return -1;
			length = this.length;
			if(!beginPosition)	beginPosition = 0;
			else if(Math.abs(beginPosition) > length)	return -1;
			else if(beginPosition <= -1)
					beginPosition = (length + beginPosition) % length;
			for(i = length; i >= 0; i--){
				if(this[i] == val)	return i;
			}
			return -1;
		}
	}
	if(!Array.prototype.allIndexOf){
		// 查找原数组中所有值为val的数组的索引，beginPosition为可选参数，-1表示末尾(以此类推)
		// 需要方法firstIndexOf的支持
		// 返回所有索引组成的新数组
		Array.prototype.allIndexOf = function(val, beginPosition){
			var ret = []
				,length = 0;
			if(!this)	return [];
			length = this.length;
			if(!beginPosition)	beginPosition = 0;
			else if(Math.abs(beginPosition) > length)	return [];
			else if(beginPosition <= -1)
					beginPosition = (length + beginPosition) % length;
			while(beginPosition < length){
				beginPosition = this.firstIndexOf(val, beginPosition);
				if(beginPosition == -1)	break;
				ret.push(beginPosition++);
			}
			return ret;
		}
	}
	if(!Array.prototype.countMatch){
		// 查找匹配字符串出现的次数
		Array.prototype.countMatch = function(matchStr){
			if(!this || matchStr === undefined)	return 0;
			return this.allIndexOf(matchStr).length;
		}
	}
	if(!Array.prototype.contain){
		// 验证数组是否包含字符串val，是返回true，否则返回false
		Array.prototype.contain = function(val){
			var reg = new RegExp(this.S + val + this.S);
			return (reg.test(this.S + this.join(this.S) + this.S));
		}
	}
	if(!Array.prototype.containObject){
		// 验证数组是否包含对象val，是返回true，否则返回false
		Array.prototype.containObject = function(val){
			return this.firstIndexOf(val) >= 0;
		}
	}
	if(!Array.prototype.every){
		// 对原数组中的每个元素执行函数func，如果所有的元素都符合函数中给定的条件则返回true，否则返回false
		// 函数func开放参数(this[i], i, this), 函数必须要有返回值，否则默认返回值将被处理为undefined而导致该函数解析出错
		Array.prototype.every = function(func){
			var i = 0
				,length = this.length;
			if(length <= 0 || !isFunction(func))	return false;
			for(i = 0; i < length; i++){
				if(!func.call(this, this[i], i, this))	return false;
			}
			return true;
		}
	}
	if(!Array.prototype.some){
		// 对数组中的每个元素执行函数func，如果存在至少一个元素符合函数中给定的条件则返回true，否则返回false
		// 函数func开放参数(this[i], i, this), 函数必须要有返回值，否则默认返回值将被处理为undefined而导致该函数解析出错
		Array.prototype.some = function(func){
			var i = 0
				,length = this.length;
			if(length <= 0 || !isFunction(func))	return false;
			for(i = 0; i < length; i++){
				if(func.call(this, this[i], i, this))	return true;
			}
			return false;
		}
	}
	if(!Array.prototype.filter){
		// 对数组的每个元素都执行给定的函数，并返回符合函数条件组成的新数组
		// 函数func开放参数(this[i], i, this), 函数必须要有返回值，否则默认返回值将被处理为undefined而导致该函数解析出错
		Array.prototype.filter = function(func){
			var b = null
				,i = 0
				,ret = []
				,length = this.length;
			if(length <= 0 || !isFunction(func))	return [];
			for(i = 0; i < length; i++){
				b = func.call(this, this[i], i, this);
				if(b === true)	ret.push(this[i]);
			}
			return ret;
		}
	}
	if(!Array.prototype.forEach){
		// 对数组进行遍历并对每个元素执行给定的函数，函数中返回false则结束本次遍历
		// 函数func开放参数(this[i], i, this), 函数必须要有返回值，否则默认返回值将被处理为undefined而导致该函数解析出错
		Array.prototype.forEach = function(func){
			var i = 0
				,length = this.length
				,b = null;
			if(length <= 0 || !isFunction(func))	return false;
			for(i = 0; i < length; i++){
				b = func.call(this, this[i], i, this);
				if(b === false)	break;
			}
			return this;
		}
	}
	if(!Array.prototype.max){
		// 取数组中的最大值,数组必须为数值型，否则返回NaN
		Array.prototype.max = function(){
			return Math.max.apply(Math, this);
		}
	}
	if(!Array.prototype.min){
		// 取数组中的最小值，数组必须为数值型，否则返回NaN
		Array.prototype.min = function(){
			return Math.min.apply(Math, this);
		}
	}
	if(!Array.prototype.sum){
		// 数组求和，若参数isCast=true，则函数将试图对每个元素进行强制转换后求和
		Array.prototype.sum = function(isCast){
			var sum = 0;
			this.forEach(function(v, i, arr){
				if(isCast && Object.prototype.toString.call(v) == "[object String]"){
					if(!isNaN(v))	v = parseInt(v);
				}
				if(Object.prototype.toString.call(v) == "[object Number]"){
					sum += v;
				}
				return true;
			});
			return sum;
		}
	}
	if(!Array.prototype.parseOneDimArray){
		// 将多维数组展开为一维数组后添加到ret数组后
		Array.prototype.parseOneDimArray = function(ret){
			if(!ret || !isArray(ret))	ret = [];
			for(var i = 0, length = this.length; i < length; i++){
				if(this[i].constructor == Array)	this[i].parseOneDimArray(ret);
				else	ret.push(this[i]);
			}
			return ret;
		}
	}
	if(!Array.prototype.unique){
		// 数组去重，返回一个新数组
		Array.prototype.unique = function(){
			var obj = {}
				,num = []
				,i = 0
				,length = this.length;
			for(i = 0; i < length; i++){
				if(!obj[typeof (this[i]) + this[i]]){
					num.push(this[i]);
					obj[typeof (this[i]) + this[i]] = '1';
				}
			}
			return num;
		}
	}
	if(!Array.prototype.removeVal){
		// 根据值删除数组元素,删除后将减小数组长度
		Array.prototype.removeVal = function(val, delAll){
			var indexArr = this.allIndexOf(val, 0);
			if(indexArr.length > 0){
				if(!delAll){
					this.splice(indexArr[0], 1);
				}else{
					for ( var i = 0; i < indexArr.length; i++) {
						this.splice(indexArr[0], 1);
					}
				}
			}
			return this;
		}
	}
	if(!Array.prototype.removeAt){
		// 根据索引删除数组元素，删除后将减小数组长度
		Array.prototype.removeAt = function(index){
			if(index < 0 || index > this.length)	return this;
			this.splice(index, 1);
			return this;
		}
	}
	if(!Array.prototype.swap){
		// 对调数组中两个索引对应的值
		Array.prototype.swap = function(tarIndex, golIndex){
			var max = Math.max(tarIndex, golIndex),
				min = Math.min(tarIndex, golIndex),
				remove = this.splice(max, 1, this[min]);
			this.splice(min, 1, remove[0]);
			return this;
		}
	}
	if(!Array.prototype.get){
		Array.prototype.get = function(index){
			if(index > this.length || index < 0)	return null;
			return this[index];
		}
	}
	if(!Array.prototype.first){
		Array.prototype.first = function(object){
			if(object === undefined || object === null || !this.contain(object))	return this.get(0);
			return this.get(this.firstIndexOf(object, 0) + 1);
		}
	}
	if(!Array.prototype.last){
		Array.prototype.last = function(object){
			if(object === undefined || object === null || !this.contain(object))	return this.get(this.length - 1);
			return this.get(this.lastIndexOf(object, 0) - 1);
		}
	}
	
	// ****************************************************************************************//
	// String 扩展
	if(!String.prototype.getChinese){
		// 保留字符串中的中文,并以字符串s为分隔符分隔开
		String.prototype.getChinese = function(s){
			if(!s)	s = "";
			return this.match(/^[\u2E80-\u9FFF]+$/g).join(s);
		}
	}
	
	if(!String.prototype.getLetter){
		// 保留字符串中的字母，并以字符串s为分隔符分隔开
		String.prototype.getLetter = function(s){
			if(!s) s = "";
			return this.match(/[a-zA-Z]/g).join(s);
		}
	}
	
	if(!String.prototype.getNum){
		// 保留字符串中的数字,并以字符串s为分隔符分隔开
		String.prototype.getNum = function(s){
			if(!s) s = "";
			return this.match(/[\d]/g).join(s);
		}
	}
	
	if(!String.prototype.isInt){
		// 判断字符串是否为整数
		String.prototype.isInt = function(){
			if(this !== this)	return false;			// NaN !== NaN
			return this == parseInt(this).toString();
		}
	}
	
	if(!String.prototype.repeat){
		// 获取N个相同的字符串
		String.prototype.repeat = function(num){
			var tmpArr = []
				,i = 0;
			for(i = 0; i < num; i++){
				tmpArr.push(this);
			}
			return tmpArr.join("");
		}
	}
	
	if(!String.prototype.isNumeric){
		// 判断字符串是否为数字,小数尾数为0的暂时不可判断
		String.prototype.isNumeric = function(){
			var tmpFloat = parseFloat(this)
				,tmpLen = this.length - tmpFloat.toString().length;
			if(isNaN(tmpFloat))		return false;;
			return "0".repeat(tmpLen) + tmpFloat == this;
		}
	}
	if(!String.prototype.lTrim){
		// 去除左边空白
		String.prototype.lTrim = function(){
			return this.replace(/^\s+/g, "");
		}
	}
	if(!String.prototype.rTrim){
		// 去除右边空白
		String.prototype.rTrim = function(){
			return this.replace(/\s+$/g, "");
		}
	}
	if(!String.prototype.trim){
		// 去除两端空白
		String.prototype.trim = function(){
			return this.replace(/(^\s+)|(\s+$)/g, "");
		}
	}
	if(!String.prototype.resetBlank){
		// 合并多个空白为一个空白
		String.prototype.resetBlank = function(){
			return this.replace(/\s+/g, " ");
		}
	}
	if(!String.prototype.reverse){
		// 逆序输出
		String.prototype.reverse = function(){
			return this.split("").reverse().join("");
		}
	}
	if(!String.prototype.abbr){
		// 缩减字符串，多出部分用"..."替代
		String.prototype.abbr = function(width){
			var length = this.length
			if(!this || length <= 0 || !width || width <= 0)	return "";
			if(width >= length)	return this;
			else if(width >= 0){
				return this.substr(0, width) + "...";
			}
			return "";
		}
	}
	if(!String.prototype.countMatchChar){
		// 查找匹配字符出现的次数
		String.prototype.countMatchChar = function(matchChar){
			return this.split("").countMatch(matchChar);
		}
	}
	if(!String.prototype.countMathString){
		// 查找匹配字符串出现的次数
		// 当repeat设置为true时，查找到匹配的字符串后将继续从匹配字符串的第二个字符开始往下查找下一次的匹配，否则将从匹配字符串之后的字符开始查找
		String.prototype.countMathString = function(matchStr, repeat, index){
			var count = 0;
			if(!index)	index = 0;
			while((index = this.indexOf(matchStr, index)) != -1){
				repeat ? index++ : index += matchStr.length;
				count++;
			}	
			return count;
					
		}
	}
	if(!String.prototype.startWith){
		// 判断字符串是否是以sub开始的
		String.prototype.startWith = function(sub){
			var sReg = eval("/^" + sub + "/");
			return sReg.test(this);
		}
	}
	if(!String.prototype.endWith){
		// 判断字符串是否是以sub结尾的
		String.prototype.endWith = function(sub){
			var sReg = eval("/" + sub + "$/");
			return sReg.test(this);
		}
	}
	if(!String.prototype.camelize){
		// 字符串分隔符delimiter转驼峰，或者驼峰转delimiter，
		// isHump=true表示转驼峰，否则转其他
		String.prototype.camelize = function(delimiter, isHump){
			isHump = !!isHump;
			if(!delimiter || Object.prototype.toString.call(delimiter).toLowerCase() != "[object string]")	return;
			if(!isHump){
				return this.replace(/([A-Z])/g, delimiter + "$1").toLowerCase();
			}else{
				return this.replace(eval("/" + delimiter + "(\\w)/g"), function(all, letter){
				          return letter.toUpperCase();
				});
			}
		}
	}
	if(!String.prototype.pxToInt){
		// 将200px == > 200
		String.prototype.pxToInt = function(){
	        return parseInt(this.split('px')[0]);
	    }
	}
	if(!String.prototype.isHex){
		// 判断字符串是否16进制
		window.defineProperty(String.prototype, 'isHex',{
	        value: function(){
	            return this.substring(0,1) == '#' &&  
	                   (this.length == 4 || this.length == 7) && 
	                   /^[0-9a-fA-F]+$/.test(this.slice(1));
	        },
	        enumerable: false
	    });
	}
	if(!String.prototype.transformToCharCode){
		/**
		 * html特殊字符实体映射表
		 */
		String.prototype.specialSymbols = { 
				38: "&amp;",                    
				34: "&quot;",                   
				39: "&apos;",                   
				60: "&lt;",                     
				62: "&gt;"                      
		};
		/**
		 * 将字符串中的特殊符号转义为html编码，非特殊符号不转换
		 * @returns
		 */
		String.prototype.transformToCharCode = function(){
			var arr = this.split(""), code;
			for(var i = 0, l = arr.length; i < l; i++){
				code = this.specialSymbols[arr[i]]
				if(code){
					arr[i] = code;
				}
			}
			return arr.join("");
		}
	}
	/*
	if(!String.prototype.difference){
		// 计算字符串差别
		String.prototype.difference = function(strTwo){
		
			var l1 = this.length
				,l2 = strTwo.length
				,l = 0
				,i = 0
				,j = 0
				,tmpStr
				,s;
			if(!this)	return !strTwo ? "" : strTwo;
			if(l1 > l2){
				tmpStr = strTwo;
				strTwo = this;
			}else{
				tmpStr = this;
			}
			for(l = tmpStr.length, i = 0; i < l; i++){
				for(j = 0; j <= i; j++){
					s = tmpStr.substring(j, j + (l - i));
					
				}
			}
		}
	}*/
	// ****************************************************************************************//
	// Date 扩展
	if(!Date.prototype.format){
		// 日期格式化,yyyy-MM-dd hh:mm:ss
		Date.prototype.format = function(format){
			var o = { 
			"M+" : this.getMonth() + 1, 						// month
			"d+" : this.getDate(), 								// day
			"h+" : this.getHours(), 							// hour
			"m+" : this.getMinutes(), 							// minute
			"s+" : this.getSeconds(), 							// second
			"q+" : Math.floor((this.getMonth() + 3)/3),		// quarter
			"S" : this.getMilliseconds() 						// millisecond
		} 
		if(/(y+)/.test(format)) { 
			format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		} 

		for(var k in o) { 
			if(new RegExp("("+ k +")").test(format)) { 
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
			} 
		} 
			return format; 
		}
	}
	
	// ****************************************************************************************//
	// Json 扩展
	if(!window.Json)	window.Json = {};
	if(!Json.parseJson){
		// 将字符串转化为Json
		Json.parseJson = function(jTxt){
			var rvalidchars = /^[\],:{}\s]*$/
				,rvalidescape = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g
				,rvalidtokens = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g
				,rvalidbraces = /(?:^|:|,)(?:\s*\[)+/g;
			if ( rvalidchars.test( jTxt.replace( rvalidescape, "@" )
										.replace( rvalidtokens, "]" )
										.replace( rvalidbraces, "")) ) {
				return ( new Function( "return " + jTxt ) )();
			}
		}
	}

		// 将json转化为字符串输出 
		Json.toString = function(json){
			var res = "{"
				,i = -1;
			for(i in json)	res += '"' + i + '":"' + json[i] + '",';
			return i != -1 ? res.substr(0, res.length - 1) + "}" : res += "}";
		}

	// ****************************************************************************************//
	// Object 扩展
//	if(!Object.prototype.getClass){
		// 返回对象的类型名
//		Object.prototype.getClass = function(){
//			var type = Object.prototype.toString.call(this).slice(8, -1);
//			if(type == "Object" && isFunction(this) && this.constructor)	return this.constructor.toString().match(/(?: )[\w\$]+/)[0];
//			return type;
//		}
//	}
	// ****************************************************************************************//
	// Function 扩展
	if(!Function.prototype.getName){
		Function.prototype.getName = function(){
			if("name" in this)	return this.name;
			return this.name = this.toString().match(/function\s*([^(]*)\(/)[1];
		}
	}
	

	
})( window );