function LocalCache(cacheName) {
	this.cacheName = cacheName || "lxCache" + "|";
	this.chcheTypeRegExp = /^\w*>/;
}

LocalCache.prototype = {
	/**
	 * set
	 * @param name
	 * @param data
	 */
	set : function(name, data) {
		if (!name || !data) {
			return;
		}
		name = this.cacheName + name;
		var dataType = typeof data;
		var cacheData;
		switch (dataType) {
			case "object":
				cacheData = dataType + ">" + JSON.stringify(data);
				break;
			case "boolean":
			case "number":
				cacheData = dataType + ">" + Number(data);
				break;
			default:
				cacheData = dataType + ">" + data;
				break;
		}
		window.api.setPrefs({
			key : name,
			value : cacheData
		});
	},
	/**
	 * get
	 * @param name
	 * @returns {*}
	 */
	get : function(name) {
		var cacheName = this.cacheName + name;
		var cacheDataTemp = window.api.getPrefs({
			key : cacheName,
			sync : true
		});
		if (!cacheDataTemp) {
			return null;
		}
		var cacheTypeTemp = cacheDataTemp.match(this.chcheTypeRegExp)[0];
		if (!cacheTypeTemp) {
			return cacheDataTemp;
		}
		var cacheType = cacheTypeTemp.replace(">", "");
		var cacheData = cacheDataTemp.replace(cacheTypeTemp, "");
		switch (cacheType) {
			case "object":
				return JSON.parse(cacheData);
			case "boolean":
				return Boolean(cacheData);
			case "number":
				return Number(cacheData);
			default:
				return cacheData;
		}
	},
	clear : function(name) {
		var cacheName = this.cacheName + name;
		window.api.removePrefs({
			key : cacheName
		});
	},
	clearAll : function() {

	}
};

function BrowseRecords(cacheName) {
	this.localCache = new LocalCache();
	this.recordsCacheName = cacheName || "browsRecords";
}

BrowseRecords.prototype = {
	setHistory : function(record) {
		var recordList = this.localCache.get(this.recordsCacheName) || [];
		//去除ID相同的历史记录
		for (var i = 0; i < recordList.length; i++) {
			if (recordList[i].id === record.id) {
				recordList.splice(i, 1);
				break;
			}
		}
		recordList.unshift(record);
		this.localCache.set(this.recordsCacheName, recordList);
	},
	getHistory : function() {
		//足迹去除三十天前的历史记录
		var recordListTemp = this.localCache.get(this.recordsCacheName) || [];
		var endDate = 30 * 24 * 60 * 60 * 1000;
		//  alert(endDate)
		var recordList = [];
		for (var i = 0; i < recordListTemp.length; i++) {
			if (Date.now() - recordListTemp[i].lastSeeDate < endDate) {
				recordList.push(recordListTemp[i]);
			}
		}
		this.localCache.set(this.recordsCacheName, recordList);
		return recordList;
	},
	getHistorys : function() {
		//收藏去除三十天前的历史记录
		var recordListTemp = this.localCache.get(this.recordsCacheName) || [];
		var endDate = 600 * 24 * 60 * 60 * 1000;
		// alert(endDate)
		var recordList = [];
		for (var i = 0; i < recordListTemp.length; i++) {
			if (Date.now() - recordListTemp[i].lastSeeDate < endDate) {
				recordList.push(recordListTemp[i]);
			}
		}
		this.localCache.set(this.recordsCacheName, recordList);
		return recordList;
	},
	getHistoryshop : function() {
		//店铺去除三十天前的历史记录
		var recordListTemp = this.localCache.get(this.recordsCacheName) || [];
		var endDate = 600 * 24 * 60 * 60 * 1000;
		// alert(endDate)
		var recordList = [];
		for (var i = 0; i < recordListTemp.length; i++) {
			if (Date.now() - recordListTemp[i].lastSeeDate < endDate) {
				recordList.push(recordListTemp[i]);
			}
		}
		this.localCache.set(this.recordsCacheName, recordList);
		return recordList;
	},
	clearHistory : function() {
		this.localCache.clear(this.recordsCacheName);
	},
	clearHistoryById : function(id) {
		var recordList = this.localCache.get(this.recordsCacheName) || [];
		//去除ID相同的历史记录
		for (var i = 0; i < recordList.length; i++) {
			if (recordList[i].id === id) {
				recordList.splice(i, 1);
				break;
			}
		}
		this.localCache.set(this.recordsCacheName, recordList);
	}
};

