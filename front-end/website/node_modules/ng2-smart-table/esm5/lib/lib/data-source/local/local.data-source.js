import { __extends } from "tslib";
import { LocalSorter } from './local.sorter';
import { LocalFilter } from './local.filter';
import { LocalPager } from './local.pager';
import { DataSource } from '../data-source';
import { deepExtend } from '../../helpers';
var LocalDataSource = /** @class */ (function (_super) {
    __extends(LocalDataSource, _super);
    function LocalDataSource(data) {
        if (data === void 0) { data = []; }
        var _this = _super.call(this) || this;
        _this.data = [];
        _this.filteredAndSorted = [];
        _this.sortConf = [];
        _this.filterConf = {
            filters: [],
            andOperator: true,
        };
        _this.pagingConf = {};
        _this.data = data;
        return _this;
    }
    LocalDataSource.prototype.load = function (data) {
        this.data = data;
        return _super.prototype.load.call(this, data);
    };
    LocalDataSource.prototype.prepend = function (element) {
        this.reset(true);
        this.data.unshift(element);
        return _super.prototype.prepend.call(this, element);
    };
    LocalDataSource.prototype.append = function (element) {
        this.reset(true);
        this.data.push(element);
        return _super.prototype.append.call(this, element);
    };
    LocalDataSource.prototype.add = function (element) {
        this.data.push(element);
        return _super.prototype.add.call(this, element);
    };
    LocalDataSource.prototype.remove = function (element) {
        this.data = this.data.filter(function (el) { return el !== element; });
        return _super.prototype.remove.call(this, element);
    };
    LocalDataSource.prototype.update = function (element, values) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.find(element).then(function (found) {
                found = deepExtend(found, values);
                _super.prototype.update.call(_this, found, values).then(resolve).catch(reject);
            }).catch(reject);
        });
    };
    LocalDataSource.prototype.find = function (element) {
        var found = this.data.find(function (el) { return el === element; });
        if (found) {
            return Promise.resolve(found);
        }
        return Promise.reject(new Error('Element was not found in the dataset'));
    };
    LocalDataSource.prototype.getElements = function () {
        var data = this.data.slice(0);
        return Promise.resolve(this.prepareData(data));
    };
    LocalDataSource.prototype.getFilteredAndSorted = function () {
        var data = this.data.slice(0);
        this.prepareData(data);
        return Promise.resolve(this.filteredAndSorted);
    };
    LocalDataSource.prototype.getAll = function () {
        var data = this.data.slice(0);
        return Promise.resolve(data);
    };
    LocalDataSource.prototype.reset = function (silent) {
        if (silent === void 0) { silent = false; }
        if (silent) {
            this.filterConf = {
                filters: [],
                andOperator: true,
            };
            this.sortConf = [];
            this.pagingConf['page'] = 1;
        }
        else {
            this.setFilter([], true, false);
            this.setSort([], false);
            this.setPage(1);
        }
    };
    LocalDataSource.prototype.empty = function () {
        this.data = [];
        return _super.prototype.empty.call(this);
    };
    LocalDataSource.prototype.count = function () {
        return this.filteredAndSorted.length;
    };
    /**
     *
     * Array of conf objects
     * [
     *  {field: string, direction: asc|desc|null, compare: Function|null},
     * ]
     * @param conf
     * @param doEmit
     * @returns {LocalDataSource}
     */
    LocalDataSource.prototype.setSort = function (conf, doEmit) {
        if (doEmit === void 0) { doEmit = true; }
        if (conf !== null) {
            conf.forEach(function (fieldConf) {
                if (!fieldConf['field'] || typeof fieldConf['direction'] === 'undefined') {
                    throw new Error('Sort configuration object is not valid');
                }
            });
            this.sortConf = conf;
        }
        _super.prototype.setSort.call(this, conf, doEmit);
        return this;
    };
    /**
     *
     * Array of conf objects
     * [
     *  {field: string, search: string, filter: Function|null},
     * ]
     * @param conf
     * @param andOperator
     * @param doEmit
     * @returns {LocalDataSource}
     */
    LocalDataSource.prototype.setFilter = function (conf, andOperator, doEmit) {
        var _this = this;
        if (andOperator === void 0) { andOperator = true; }
        if (doEmit === void 0) { doEmit = true; }
        if (conf && conf.length > 0) {
            conf.forEach(function (fieldConf) {
                _this.addFilter(fieldConf, andOperator, false);
            });
        }
        else {
            this.filterConf = {
                filters: [],
                andOperator: true,
            };
        }
        this.filterConf.andOperator = andOperator;
        this.pagingConf['page'] = 1;
        _super.prototype.setFilter.call(this, conf, andOperator, doEmit);
        return this;
    };
    LocalDataSource.prototype.addFilter = function (fieldConf, andOperator, doEmit) {
        var _this = this;
        if (andOperator === void 0) { andOperator = true; }
        if (doEmit === void 0) { doEmit = true; }
        if (!fieldConf['field'] || typeof fieldConf['search'] === 'undefined') {
            throw new Error('Filter configuration object is not valid');
        }
        var found = false;
        this.filterConf.filters.forEach(function (currentFieldConf, index) {
            if (currentFieldConf['field'] === fieldConf['field']) {
                _this.filterConf.filters[index] = fieldConf;
                found = true;
            }
        });
        if (!found) {
            this.filterConf.filters.push(fieldConf);
        }
        this.filterConf.andOperator = andOperator;
        _super.prototype.addFilter.call(this, fieldConf, andOperator, doEmit);
        return this;
    };
    LocalDataSource.prototype.setPaging = function (page, perPage, doEmit) {
        if (doEmit === void 0) { doEmit = true; }
        this.pagingConf['page'] = page;
        this.pagingConf['perPage'] = perPage;
        _super.prototype.setPaging.call(this, page, perPage, doEmit);
        return this;
    };
    LocalDataSource.prototype.setPage = function (page, doEmit) {
        if (doEmit === void 0) { doEmit = true; }
        this.pagingConf['page'] = page;
        _super.prototype.setPage.call(this, page, doEmit);
        return this;
    };
    LocalDataSource.prototype.getSort = function () {
        return this.sortConf;
    };
    LocalDataSource.prototype.getFilter = function () {
        return this.filterConf;
    };
    LocalDataSource.prototype.getPaging = function () {
        return this.pagingConf;
    };
    LocalDataSource.prototype.prepareData = function (data) {
        data = this.filter(data);
        data = this.sort(data);
        this.filteredAndSorted = data.slice(0);
        return this.paginate(data);
    };
    LocalDataSource.prototype.sort = function (data) {
        if (this.sortConf) {
            this.sortConf.forEach(function (fieldConf) {
                data = LocalSorter
                    .sort(data, fieldConf['field'], fieldConf['direction'], fieldConf['compare']);
            });
        }
        return data;
    };
    // TODO: refactor?
    LocalDataSource.prototype.filter = function (data) {
        if (this.filterConf.filters) {
            if (this.filterConf.andOperator) {
                this.filterConf.filters.forEach(function (fieldConf) {
                    if (fieldConf['search'].length > 0) {
                        data = LocalFilter
                            .filter(data, fieldConf['field'], fieldConf['search'], fieldConf['filter']);
                    }
                });
            }
            else {
                var mergedData_1 = [];
                this.filterConf.filters.forEach(function (fieldConf) {
                    if (fieldConf['search'].length > 0) {
                        mergedData_1 = mergedData_1.concat(LocalFilter
                            .filter(data, fieldConf['field'], fieldConf['search'], fieldConf['filter']));
                    }
                });
                // remove non unique items
                data = mergedData_1.filter(function (elem, pos, arr) {
                    return arr.indexOf(elem) === pos;
                });
            }
        }
        return data;
    };
    LocalDataSource.prototype.paginate = function (data) {
        if (this.pagingConf && this.pagingConf['page'] && this.pagingConf['perPage']) {
            data = LocalPager.paginate(data, this.pagingConf['page'], this.pagingConf['perPage']);
        }
        return data;
    };
    return LocalDataSource;
}(DataSource));
export { LocalDataSource };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoibG9jYWwuZGF0YS1zb3VyY2UuanMiLCJzb3VyY2VSb290Ijoibmc6Ly9uZzItc21hcnQtdGFibGUvIiwic291cmNlcyI6WyJsaWIvbGliL2RhdGEtc291cmNlL2xvY2FsL2xvY2FsLmRhdGEtc291cmNlLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQSxPQUFPLEVBQUUsV0FBVyxFQUFFLE1BQU0sZ0JBQWdCLENBQUM7QUFDN0MsT0FBTyxFQUFFLFdBQVcsRUFBRSxNQUFNLGdCQUFnQixDQUFDO0FBQzdDLE9BQU8sRUFBRSxVQUFVLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFDM0MsT0FBTyxFQUFFLFVBQVUsRUFBRSxNQUFNLGdCQUFnQixDQUFDO0FBQzVDLE9BQU8sRUFBRSxVQUFVLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFFM0M7SUFBcUMsbUNBQVU7SUFXN0MseUJBQVksSUFBcUI7UUFBckIscUJBQUEsRUFBQSxTQUFxQjtRQUFqQyxZQUNFLGlCQUFPLFNBR1I7UUFiUyxVQUFJLEdBQWUsRUFBRSxDQUFDO1FBQ3RCLHVCQUFpQixHQUFlLEVBQUUsQ0FBQztRQUNuQyxjQUFRLEdBQWUsRUFBRSxDQUFDO1FBQzFCLGdCQUFVLEdBQVE7WUFDMUIsT0FBTyxFQUFFLEVBQUU7WUFDWCxXQUFXLEVBQUUsSUFBSTtTQUNsQixDQUFDO1FBQ1EsZ0JBQVUsR0FBUSxFQUFFLENBQUM7UUFLN0IsS0FBSSxDQUFDLElBQUksR0FBRyxJQUFJLENBQUM7O0lBQ25CLENBQUM7SUFFRCw4QkFBSSxHQUFKLFVBQUssSUFBZ0I7UUFDbkIsSUFBSSxDQUFDLElBQUksR0FBRyxJQUFJLENBQUM7UUFFakIsT0FBTyxpQkFBTSxJQUFJLFlBQUMsSUFBSSxDQUFDLENBQUM7SUFDMUIsQ0FBQztJQUVELGlDQUFPLEdBQVAsVUFBUSxPQUFZO1FBQ2xCLElBQUksQ0FBQyxLQUFLLENBQUMsSUFBSSxDQUFDLENBQUM7UUFFakIsSUFBSSxDQUFDLElBQUksQ0FBQyxPQUFPLENBQUMsT0FB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Q0FBQyxFQUFFLElBQUksQ0FBQyxVQUFVLENBQUMsU0FBUyxDQUFDLENBQUMsQ0FBQztTQUN2RjtRQUNELE9BQU8sSUFBSSxDQUFDO0lBQ2QsQ0FBQztJQUNILHNCQUFDO0FBQUQsQ0FBQyxBQW5RRCxDQUFxQyxVQUFVLEdBbVE5QyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IExvY2FsU29ydGVyIH0gZnJvbSAnLi9sb2NhbC5zb3J0ZXInO1xuaW1wb3J0IHsgTG9jYWxGaWx0ZXIgfSBmcm9tICcuL2xvY2FsLmZpbHRlcic7XG5pbXBvcnQgeyBMb2NhbFBhZ2VyIH0gZnJvbSAnLi9sb2NhbC5wYWdlcic7XG5pbXBvcnQgeyBEYXRhU291cmNlIH0gZnJvbSAnLi4vZGF0YS1zb3VyY2UnO1xuaW1wb3J0IHsgZGVlcEV4dGVuZCB9IGZyb20gJy4uLy4uL2hlbHBlcnMnO1xuXG5leHBvcnQgY2xhc3MgTG9jYWxEYXRhU291cmNlIGV4dGVuZHMgRGF0YVNvdXJjZSB7XG5cbiAgcHJvdGVjdGVkIGRhdGE6IEFycmF5PGFueT4gPSBbXTtcbiAgcHJvdGVjdGVkIGZpbHRlcmVkQW5kU29ydGVkOiBBcnJheTxhbnk+ID0gW107XG4gIHByb3RlY3RlZCBzb3J0Q29uZjogQXJyYXk8YW55PiA9IFtdO1xuICBwcm90ZWN0ZWQgZmlsdGVyQ29uZjogYW55ID0ge1xuICAgIGZpbHRlcnM6IFtdLFxuICAgIGFuZE9wZXJhdG9yOiB0cnVlLFxuICB9O1xuICBwcm90ZWN0ZWQgcGFnaW5nQ29uZjogYW55ID0ge307XG5cbiAgY29uc3RydWN0b3IoZGF0YTogQXJyYXk8YW55PiA9IFtdKSB7XG4gICAgc3VwZXIoKTtcblxuICAgIHRoaXMuZGF0YSA9IGRhdGE7XG4gIH1cblxuICBsb2FkKGRhdGE6IEFycmF5PGFueT4pOiBQcm9taXNlPGFueT4ge1xuICAgIHRoaXMuZGF0YSA9IGRhdGE7XG5cbiAgICByZXR1cm4gc3VwZXIubG9hZChkYXRhKTtcbiAgfVxuXG4gIHByZXBlbmQoZWxlbWVudDogYW55KTogUHJvbWlzZTxhbnk+IHtcbiAgICB0aGlzLnJlc2V0KHRydWUpO1xuXG4gICAgdGhpcy5kYXRhLnVuc2hpZnQoZWxlbWVudCk7XG4gICAgcmV0dXJuIHN1cGVyLnByZXBlbmQoZWxlbWVudCk7XG4gIH1cblxuICBhcHBlbmQoZWxlbWVudDogYW55KTogUHJvbWlzZTxhbnk+IHtcbiAgICB0aGlzLnJlc2V0KHRydWUpO1xuXG4gICAgdGhpcy5kYXRhLnB1c2goZWxlbWVudCk7XG4gICAgcmV0dXJuIHN1cGVyLmFwcGVuZChlbGVtZW50KTtcbiAgfVxuXG4gIGFkZChlbGVtZW50OiBhbnkpOiBQcm9taXNlPGFueT4ge1xuICAgIHRoaXMuZGF0YS5wdXNoKGVsZW1lbnQpO1xuXG4gICAgcmV0dXJuIHN1cGVyLmFkZChlbGVtZW50KTtcbiAgfVxuXG4gIHJlbW92ZShlbGVtZW50OiBhbnkpOiBQcm9taXNlPGFueT4ge1xuICAgIHRoaXMuZGF0YSA9IHRoaXMuZGF0YS5maWx0ZXIoZWwgPT4gZWwgIT09IGVsZW1lbnQpO1xuXG4gICAgcmV0dXJuIHN1cGVyLnJlbW92ZShlbGVtZW50KTtcbiAgfVxuXG4gIHVwZGF0ZShlbGVtZW50OiBhbnksIHZhbHVlczogYW55KTogUHJvbWlzZTxhbnk+IHtcbiAgICByZXR1cm4gbmV3IFByb21pc2UoKHJlc29sdmUsIHJlamVjdCkgPT4ge1xuICAgICAgdGhpcy5maW5kKGVsZW1lbnQpLnRoZW4oKGZvdW5kKSA9PiB7XG4gICAgICAgIGZvdW5kID0gZGVlcEV4dGVuZChmb3VuZCwgdmFsdWVzKTtcbiAgICAgICAgc3VwZXIudXBkYXRlKGZvdW5kLCB2YWx1ZXMpLnRoZW4ocmVzb2x2ZSkuY2F0Y2gocmVqZWN0KTtcbiAgICAgIH0pLmNhdGNoKHJlamVjdCk7XG4gICAgfSk7XG4gIH1cblxuICBmaW5kKGVsZW1lbnQ6IGFueSk6IFByb21pc2U8YW55PiB7XG4gICAgY29uc3QgZm91bmQgPSB0aGlzLmRhdGEuZmluZChlbCA9PiBlbCA9PT0gZWxlbWVudCk7XG4gICAgaWYgKGZvdW5kKSB7XG4gICAgICByZXR1cm4gUHJvbWlzZS5yZXNvbHZlKGZvdW5kKTtcbiAgICB9XG5cbiAgICByZXR1cm4gUHJvbWlzZS5yZWplY3QobmV3IEVycm9yKCdFbGVtZW50IHdhcyBub3QgZm91bmQgaW4gdGhlIGRhdGFzZXQnKSk7XG4gIH1cblxuICBnZXRFbGVtZW50cygpOiBQcm9taXNlPGFueT4ge1xuICAgIGNvbnN0IGRhdGEgPSB0aGlzLmRhdGEuc2xpY2UoMCk7XG4gICAgcmV0dXJuIFByb21pc2UucmVzb2x2ZSh0aGlzLnByZXBhcmVEYXRhKGRhdGEpKTtcbiAgfVxuXG4gIGdldEZpbHRlcmVkQW5kU29ydGVkKCk6IFByb21pc2U8YW55PiB7XG4gICAgbGV0IGRhdGEgPSB0aGlzLmRhdGEuc2xpY2UoMCk7XG4gICAgdGhpcy5wcmVwYXJlRGF0YShkYXRhKTtcbiAgICByZXR1cm4gUHJvbWlzZS5yZXNvbHZlKHRoaXMuZmlsdGVyZWRBbmRTb3J0ZWQpO1xuICB9XG5cbiAgZ2V0QWxsKCk6IFByb21pc2U8YW55PiB7XG4gICAgY29uc3QgZGF0YSA9IHRoaXMuZGF0YS5zbGljZSgwKTtcbiAgICByZXR1cm4gUHJvbWlzZS5yZXNvbHZlKGRhdGEpO1xuICB9XG5cbiAgcmVzZXQoc2lsZW50ID0gZmFsc2UpIHtcbiAgICBpZiAoc2lsZW50KSB7XG4gICAgICB0aGlzLmZpbHRlckNvbmYgPSB7XG4gICAgICAgIGZpbHRlcnM6IFtdLFxuICAgICAgICBhbmRPcGVyYXRvcjogdHJ1ZSxcbiAgICAgIH07XG4gICAgICB0aGlzLnNvcnRDb25mID0gW107XG4gICAgICB0aGlzLnBhZ2luZ0NvbmZbJ3BhZ2UnXSA9IDE7XG4gICAgfSBlbHNlIHtcbiAgICAgIHRoaXMuc2V0RmlsdGVyKFtdLCB0cnVlLCBmYWxzZSk7XG4gICAgICB0aGlzLnNldFNvcnQoW10sIGZhbHNlKTtcbiAgICAgIHRoaXMuc2V0UGFnZSgxKTtcbiAgICB9XG4gIH1cblxuICBlbXB0eSgpOiBQcm9taXNlPGFueT4ge1xuICAgIHRoaXMuZGF0YSA9IFtdO1xuXG4gICAgcmV0dXJuIHN1cGVyLmVtcHR5KCk7XG4gIH1cblxuICBjb3VudCgpOiBudW1iZXIge1xuICAgIHJldHVybiB0aGlzLmZpbHRlcmVkQW5kU29ydGVkLmxlbmd0aDtcbiAgfVxuXG4gIC8qKlxuICAgKlxuICAgKiBBcnJheSBvZiBjb25mIG9iamVjdHNcbiAgICogW1xuICAgKiAge2ZpZWxkOiBzdHJpbmcsIGRpcmVjdGlvbjogYXNjfGRlc2N8bnVsbCwgY29tcGFyZTogRnVuY3Rpb258bnVsbH0sXG4gICAqIF1cbiAgICogQHBhcmFtIGNvbmZcbiAgICogQHBhcmFtIGRvRW1pdFxuICAgKiBAcmV0dXJucyB7TG9jYWxEYXRhU291cmNlfVxuICAgKi9cbiAgc2V0U29ydChjb25mOiBBcnJheTxhbnk+LCBkb0VtaXQgPSB0cnVlKTogTG9jYWxEYXRhU291cmNlIHtcbiAgICBpZiAoY29uZiAhPT0gbnVsbCkge1xuXG4gICAgICBjb25mLmZvckVhY2goKGZpZWxkQ29uZikgPT4ge1xuICAgICAgICBpZiAoIWZpZWxkQ29uZlsnZmllbGQnXSB8fCB0eXBlb2YgZmllbGRDb25mWydkaXJlY3Rpb24nXSA9PT0gJ3VuZGVmaW5lZCcpIHtcbiAgICAgICAgICB0aHJvdyBuZXcgRXJyb3IoJ1NvcnQgY29uZmlndXJhdGlvbiBvYmplY3QgaXMgbm90IHZhbGlkJyk7XG4gICAgICAgIH1cbiAgICAgIH0pO1xuICAgICAgdGhpcy5zb3J0Q29uZiA9IGNvbmY7XG4gICAgfVxuXG4gICAgc3VwZXIuc2V0U29ydChjb25mLCBkb0VtaXQpO1xuICAgIHJldHVybiB0aGlzO1xuICB9XG5cbiAgLyoqXG4gICAqXG4gICAqIEFycmF5IG9mIGNvbmYgb2JqZWN0c1xuICAgKiBbXG4gICAqICB7ZmllbGQ6IHN0cmluZywgc2VhcmNoOiBzdHJpbmcsIGZpbHRlcjogRnVuY3Rpb258bnVsbH0sXG4gICAqIF1cbiAgICogQHBhcmFtIGNvbmZcbiAgICogQHBhcmFtIGFuZE9wZXJhdG9yXG4gICAqIEBwYXJhbSBkb0VtaXRcbiAgICogQHJldHVybnMge0xvY2FsRGF0YVNvdXJjZX1cbiAgICovXG4gIHNldEZpbHRlcihjb25mOiBBcnJheTxhbnk+LCBhbmRPcGVyYXRvciA9IHRydWUsIGRvRW1pdCA9IHRydWUpOiBMb2NhbERhdGFTb3VyY2Uge1xuICAgIGlmIChjb25mICYmIGNvbmYubGVuZ3RoID4gMCkge1xuICAgICAgY29uZi5mb3JFYWNoKChmaWVsZENvbmYpID0+IHtcbiAgICAgICAgdGhpcy5hZGRGaWx0ZXIoZmllbGRDb25mLCBhbmRPcGVyYXRvciwgZmFsc2UpO1xuICAgICAgfSk7XG4gICAgfSBlbHNlIHtcbiAgICAgIHRoaXMuZmlsdGVyQ29uZiA9IHtcbiAgICAgICAgZmlsdGVyczogW10sXG4gICAgICAgIGFuZE9wZXJhdG9yOiB0cnVlLFxuICAgICAgfTtcbiAgICB9XG4gICAgdGhpcy5maWx0ZXJDb25mLmFuZE9wZXJhdG9yID0gYW5kT3BlcmF0b3I7XG4gICAgdGhpcy5wYWdpbmdDb25mWydwYWdlJ10gPSAxO1xuXG4gICAgc3VwZXIuc2V0RmlsdGVyKGNvbmYsIGFuZE9wZXJhdG9yLCBkb0VtaXQpO1xuICAgIHJldHVybiB0aGlzO1xuICB9XG5cbiAgYWRkRmlsdGVyKGZpZWxkQ29uZjogYW55LCBhbmRPcGVyYXRvciA9IHRydWUsIGRvRW1pdDogYm9vbGVhbiA9IHRydWUpOiBMb2NhbERhdGFTb3VyY2Uge1xuICAgIGlmICghZmllbGRDb25mWydmaWVsZCddIHx8IHR5cGVvZiBmaWVsZENvbmZbJ3NlYXJjaCddID09PSAndW5kZWZpbmVkJykge1xuICAgICAgdGhyb3cgbmV3IEVycm9yKCdGaWx0ZXIgY29uZmlndXJhdGlvbiBvYmplY3QgaXMgbm90IHZhbGlkJyk7XG4gICAgfVxuXG4gICAgbGV0IGZvdW5kID0gZmFsc2U7XG4gICAgdGhpcy5maWx0ZXJDb25mLmZpbHRlcnMuZm9yRWFjaCgoY3VycmVudEZpZWxkQ29uZjogYW55LCBpbmRleDogYW55KSA9PiB7XG4gICAgICBpZiAoY3VycmVudEZpZWxkQ29uZlsnZmllbGQnXSA9PT0gZmllbGRDb25mWydmaWVsZCddKSB7XG4gICAgICAgIHRoaXMuZmlsdGVyQ29uZi5maWx0ZXJzW2luZGV4XSA9IGZpZWxkQ29uZjtcbiAgICAgICAgZm91bmQgPSB0cnVlO1xuICAgICAgfVxuICAgIH0pO1xuICAgIGlmICghZm91bmQpIHtcbiAgICAgIHRoaXMuZmlsdGVyQ29uZi5maWx0ZXJzLnB1c2goZmllbGRDb25mKTtcbiAgICB9XG4gICAgdGhpcy5maWx0ZXJDb25mLmFuZE9wZXJhdG9yID0gYW5kT3BlcmF0b3I7XG4gICAgc3VwZXIuYWRkRmlsdGVyKGZpZWxkQ29uZiwgYW5kT3BlcmF0b3IsIGRvRW1pdCk7XG4gICAgcmV0dXJuIHRoaXM7XG4gIH1cblxuICBzZXRQYWdpbmcocGFnZTogbnVtYmVyLCBwZXJQYWdlOiBudW1iZXIsIGRvRW1pdDogYm9vbGVhbiA9IHRydWUpOiBMb2NhbERhdGFTb3VyY2Uge1xuICAgIHRoaXMucGFnaW5nQ29uZlsncGFnZSddID0gcGFnZTtcbiAgICB0aGlzLnBhZ2luZ0NvbmZbJ3BlclBhZ2UnXSA9IHBlclBhZ2U7XG5cbiAgICBzdXBlci5zZXRQYWdpbmcocGFnZSwgcGVyUGFnZSwgZG9FbWl0KTtcbiAgICByZXR1cm4gdGhpcztcbiAgfVxuXG4gIHNldFBhZ2UocGFnZTogbnVtYmVyLCBkb0VtaXQ6IGJvb2xlYW4gPSB0cnVlKTogTG9jYWxEYXRhU291cmNlIHtcbiAgICB0aGlzLnBhZ2luZ0NvbmZbJ3BhZ2UnXSA9IHBhZ2U7XG4gICAgc3VwZXIuc2V0UGFnZShwYWdlLCBkb0VtaXQpO1xuICAgIHJldHVybiB0aGlzO1xuICB9XG5cbiAgZ2V0U29ydCgpOiBhbnkge1xuICAgIHJldHVybiB0aGlzLnNvcnRDb25mO1xuICB9XG5cbiAgZ2V0RmlsdGVyKCk6IGFueSB7XG4gICAgcmV0dXJuIHRoaXMuZmlsdGVyQ29uZjtcbiAgfVxuXG4gIGdldFBhZ2luZygpOiBhbnkge1xuICAgIHJldHVybiB0aGlzLnBhZ2luZ0NvbmY7XG4gIH1cblxuICBwcm90ZWN0ZWQgcHJlcGFyZURhdGEoZGF0YTogQXJyYXk8YW55Pik6IEFycmF5PGFueT4ge1xuICAgIGRhdGEgPSB0aGlzLmZpbHRlcihkYXRhKTtcbiAgICBkYXRhID0gdGhpcy5zb3J0KGRhdGEpO1xuICAgIHRoaXMuZmlsdGVyZWRBbmRTb3J0ZWQgPSBkYXRhLnNsaWNlKDApO1xuXG4gICAgcmV0dXJuIHRoaXMucGFnaW5hdGUoZGF0YSk7XG4gIH1cblxuICBwcm90ZWN0ZWQgc29ydChkYXRhOiBBcnJheTxhbnk+KTogQXJyYXk8YW55PiB7XG4gICAgaWYgKHRoaXMuc29ydENvbmYpIHtcbiAgICAgIHRoaXMuc29ydENvbmYuZm9yRWFjaCgoZmllbGRDb25mKSA9PiB7XG4gICAgICAgIGRhdGEgPSBMb2NhbFNvcnRlclxuICAgICAgICAgIC5zb3J0KGRhdGEsIGZpZWxkQ29uZlsnZmllbGQnXSwgZmllbGRDb25mWydkaXJlY3Rpb24nXSwgZmllbGRDb25mWydjb21wYXJlJ10pO1xuICAgICAgfSk7XG4gICAgfVxuICAgIHJldHVybiBkYXRhO1xuICB9XG5cbiAgLy8gVE9ETzogcmVmYWN0b3I/XG4gIHByb3RlY3RlZCBmaWx0ZXIoZGF0YTogQXJyYXk8YW55Pik6IEFycmF5PGFueT4ge1xuICAgIGlmICh0aGlzLmZpbHRlckNvbmYuZmlsdGVycykge1xuICAgICAgaWYgKHRoaXMuZmlsdGVyQ29uZi5hbmRPcGVyYXRvcikge1xuICAgICAgICB0aGlzLmZpbHRlckNvbmYuZmlsdGVycy5mb3JFYWNoKChmaWVsZENvbmY6IGFueSkgPT4ge1xuICAgICAgICAgIGlmIChmaWVsZENvbmZbJ3NlYXJjaCddLmxlbmd0aCA+IDApIHtcbiAgICAgICAgICAgIGRhdGEgPSBMb2NhbEZpbHRlclxuICAgICAgICAgICAgICAuZmlsdGVyKGRhdGEsIGZpZWxkQ29uZlsnZmllbGQnXSwgZmllbGRDb25mWydzZWFyY2gnXSwgZmllbGRDb25mWydmaWx0ZXInXSk7XG4gICAgICAgICAgfVxuICAgICAgICB9KTtcbiAgICAgIH0gZWxzZSB7XG4gICAgICAgIGxldCBtZXJnZWREYXRhOiBhbnkgPSBbXTtcbiAgICAgICAgdGhpcy5maWx0ZXJDb25mLmZpbHRlcnMuZm9yRWFjaCgoZmllbGRDb25mOiBhbnkpID0+IHtcbiAgICAgICAgICBpZiAoZmllbGRDb25mWydzZWFyY2gnXS5sZW5ndGggPiAwKSB7XG4gICAgICAgICAgICBtZXJnZWREYXRhID0gbWVyZ2VkRGF0YS5jb25jYXQoTG9jYWxGaWx0ZXJcbiAgICAgICAgICAgICAgLmZpbHRlcihkYXRhLCBmaWVsZENvbmZbJ2ZpZWxkJ10sIGZpZWxkQ29uZlsnc2VhcmNoJ10sIGZpZWxkQ29uZlsnZmlsdGVyJ10pKTtcbiAgICAgICAgICB9XG4gICAgICAgIH0pO1xuICAgICAgICAvLyByZW1vdmUgbm9uIHVuaXF1ZSBpdGVtc1xuICAgICAgICBkYXRhID0gbWVyZ2VkRGF0YS5maWx0ZXIoKGVsZW06IGFueSwgcG9zOiBhbnksIGFycjogYW55KSA9PiB7XG4gICAgICAgICAgcmV0dXJuIGFyci5pbmRleE9mKGVsZW0pID09PSBwb3M7XG4gICAgICAgIH0pO1xuICAgICAgfVxuICAgIH1cbiAgICByZXR1cm4gZGF0YTtcbiAgfVxuXG4gIHByb3RlY3RlZCBwYWdpbmF0ZShkYXRhOiBBcnJheTxhbnk+KTogQXJyYXk8YW55PiB7XG4gICAgaWYgKHRoaXMucGFnaW5nQ29uZiAmJiB0aGlzLnBhZ2luZ0NvbmZbJ3BhZ2UnXSAmJiB0aGlzLnBhZ2luZ0NvbmZbJ3BlclBhZ2UnXSkge1xuICAgICAgZGF0YSA9IExvY2FsUGFnZXIucGFnaW5hdGUoZGF0YSwgdGhpcy5wYWdpbmdDb25mWydwYWdlJ10sIHRoaXMucGFnaW5nQ29uZlsncGVyUGFnZSddKTtcbiAgICB9XG4gICAgcmV0dXJuIGRhdGE7XG4gIH1cbn1cbiJdfQ==