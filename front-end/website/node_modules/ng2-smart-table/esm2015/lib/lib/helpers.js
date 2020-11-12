import { cloneDeep } from 'lodash';
/**
 * Extending object that entered in first argument.
 *
 * Returns extended object or false if have no target object or incorrect type.
 *
 * If you wish to clone source object (without modify it), just use empty new
 * object as first argument, like this:
 *   deepExtend({}, yourObj_1, [yourObj_N]);
 */
export const deepExtend = function (...objects) {
    if (arguments.length < 1 || typeof arguments[0] !== 'object') {
        return false;
    }
    if (arguments.length < 2) {
        return arguments[0];
    }
    const target = arguments[0];
    // convert arguments to array and cut off target object
    const args = Array.prototype.slice.call(arguments, 1);
    let val, src;
    args.forEach((obj) => {
        // skip argument if it is array or isn't object
        if (typeof obj !== 'object' || Array.isArray(obj)) {
            return;
        }
        Object.keys(obj).forEach(function (key) {
            src = target[key]; // source value
            val = obj[key]; // new value
            // recursion prevention
            if (val === target) {
                return;
                /**
                 * if new value isn't object then just overwrite by new value
                 * instead of extending.
                 */
            }
            else if (typeof val !== 'object' || val === null) {
                target[key] = val;
                return;
                // just clone arrays (and recursive clone objects inside)
            }
            else if (Array.isArray(val)) {
                target[key] = cloneDeep(val);
                return;
                // overwrite by new value if source isn't object or array
            }
            else if (typeof src !== 'object' || src === null || Array.isArray(src)) {
                target[key] = deepExtend({}, val);
                return;
                // source value and new value is objects both, extending...
            }
            else {
                target[key] = deepExtend(src, val);
                return;
            }
        });
    });
    return target;
};
export class Deferred {
    constructor() {
        this.promise = new Promise((resolve, reject) => {
            this.resolve = resolve;
            this.reject = reject;
        });
    }
}
// getDeepFromObject({result: {data: 1}}, 'result.data', 2); // returns 1
export function getDeepFromObject(object = {}, name, defaultValue) {
    const keys = name.split('.');
    // clone the object
    let level = deepExtend({}, object);
    keys.forEach((k) => {
        if (level && typeof level[k] !== 'undefined') {
            level = level[k];
        }
    });
    return typeof level === 'undefined' ? defaultValue : level;
}
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaGVscGVycy5qcyIsInNvdXJjZVJvb3QiOiJuZzovL25nMi1zbWFydC10YWJsZS8iLCJzb3VyY2VzIjpbImxpYi9saWIvaGVscGVycy50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQSxPQUFPLEVBQUUsU0FBUyxFQUFFLE1BQU0sUUFBUSxDQUFDO0FBRW5DOzs7Ozs7OztHQVFHO0FBQ0gsTUFBTSxDQUFDLE1BQU0sVUFBVSxHQUFHLFVBQVMsR0FBRyxPQUFtQjtJQUN2RCxJQUFJLFNBQVMsQ0FBQyxNQUFNLEdBQUcsQ0FBQyxJQUFJLE9BQU8sU0FBUyxDQUFDLENBQUMsQ0FBQyxLQUFLLFFBQVEsRUFBRTtRQUM1RCxPQUFPLEtBQUssQ0FBQztLQUNkO0lBRUQsSUFBSSxTQUFTLENBQUMsTUFBTSxHQUFHLENBQUMsRUFBRTtRQUN4QixPQUFPLFNBQVMsQ0FBQyxDQUFDLENBQUMsQ0FBQztLQUNyQjtJQUVELE1BQU0sTUFBTSxHQUFHLFNBQVMsQ0FBQyxDQUFDLENBQUMsQ0FBQztJQUU1Qix1REFBdUQ7SUFDdkQsTUFBTSxJQUFJLEdBQUcsS0FBSyxDQUFDLFNBQVMsQ0FBQyxLQUFLLENBQUMsSUFBSSxDQUFDLFNBQVMsRUFBRSxDQUFDLENBQUMsQ0FBQztJQUV0RCxJQUFJLEdBQUcsRUFBRSxHQUFHLENBQUM7SUFFYixJQUFJLENBQUMsT0FBTyxDQUFDLENBQUMsR0FBUSxFQUFFLEVBQUU7UUFDeEIsK0NBQStDO1FBQy9DLElBQUksT0FBTyxHQUFHLEtBQUssUUFBUSxJQUFJLEtBQUssQ0FBQyxPQUFPLENBQUMsR0FBRyxDQUFDLEVBQUU7WUFDakQsT0FBTztTQUNSO1FBRUQsTUFBTSxDQUFDLElBQUksQ0FBQyxHQUFHLENBQUMsQ0FBQyxPQUFPLENBQUMsVUFBVSxHQUFHO1lBQ3BDLEdBQUcsR0FBRyxNQUFNLENBQUMsR0FBRyxDQUFDLENBQUMsQ0FBQyxlQUFlO1lBQ2xDLEdBQUcsR0FBRyxHQUFHLENBQUMsR0FBRyxDQUFDLENBQUMsQ0FBQyxZQUFZO1lBRTVCLHVCQUF1QjtZQUN2QixJQUFJLEdBQUcsS0FBSyxNQUFNLEVBQUU7Z0JBQ2xCLE9BQU87Z0JBRVA7OzttQkFHRzthQUNKO2lCQUFNLElBQUksT0FBTyxHQUFHLEtBQUssUUFBUSxJQUFJLEdBQUcsS0FBSyxJQUFJLEVBQUU7Z0JBQ2xELE1BQU0sQ0FBQyxHQUFHLENBQUMsR0FBRyxHQUFHLENBQUM7Z0JBQ2xCLE9BQU87Z0JBRVAseURBQXlEO2FBQzFEO2lCQUFNLElBQUksS0FBSyxDQUFDLE9BQU8sQ0FBQyxHQUFHLENBQUMsRUFBRTtnQkFDN0IsTUFBTSxDQUFDLEdBQUcsQ0FBQyxHQUFHLFNBQVMsQ0FBQyxHQUFHLENBQUMsQ0FBQztnQkFDN0IsT0FBTztnQkFFUCx5REFBeUQ7YUFDMUQ7aUJBQU0sSUFBSSxPQUFPLEdBQUcsS0FBSyxRQUFRLElBQUksR0FBRyxLQUFLLElBQUksSUFBSSxLQUFLLENBQUMsT0FBTyxDQUFDLEdBQUcsQ0FBQyxFQUFFO2dCQUN4RSxNQUFNLENBQUMsR0FBRyxDQUFDLEdBQUcsVUFBVSxDQUFDLEVBQUUsRUFBRSxHQUFHLENBQUMsQ0FBQztnQkFDbEMsT0FBTztnQkFFUCwyREFBMkQ7YUFDNUQ7aUJBQU07Z0JBQ0wsTUFBTSxDQUFDLEdBQUcsQ0FBQyxHQUFHLFVBQVUsQ0FBQyxHQUFHLEVBQUUsR0FBRyxDQUFDLENBQUM7Z0JBQ25DLE9BQU87YUFDUjtRQUNILENBQUMsQ0FBQyxDQUFDO0lBQ0wsQ0FBQyxDQUFDLENBQUM7SUFFSCxPQUFPLE1BQU0sQ0FBQztBQUNoQixDQUFDLENBQUM7QUFFRixNQUFNLE9BQU8sUUFBUTtJQU9uQjtRQUNFLElBQUksQ0FBQyxPQUFPLEdBQUcsSUFBSSxPQUFPLENBQUMsQ0FBQyxPQUFPLEVBQUUsTUFBTSxFQUFFLEVBQUU7WUFDN0MsSUFBSSxDQUFDLE9BQU8sR0FBRyxPQUFPLENBQUM7WUFDdkIsSUFBSSxDQUFDLE1BQU0sR0FBRyxNQUFNLENBQUM7UUFDdkIsQ0FBQyxDQUFDLENBQUM7SUFDTCxDQUFDO0NBQ0Y7QUFFRCx5RUFBeUU7QUFDekUsTUFBTSxVQUFVLGlCQUFpQixDQUFDLE1BQU0sR0FBRyxFQUFFLEVBQUUsSUFBWSxFQUFFLFlBQWtCO0lBQzdFLE1BQU0sSUFBSSxHQUFHLElBQUksQ0FBQyxLQUFLLENBQUMsR0FBRyxDQUFDLENBQUM7SUFDN0IsbUJBQW1CO0lBQ25CLElBQUksS0FBSyxHQUFHLFVBQVUsQ0FBQyxFQUFFLEVBQUUsTUFBTSxDQUFDLENBQUM7SUFDbkMsSUFBSSxDQUFDLE9BQU8sQ0FBQyxDQUFDLENBQUMsRUFBRSxFQUFFO1FBQ2pCLElBQUksS0FBSyxJQUFJLE9BQU8sS0FBSyxDQUFDLENBQUMsQ0FBQyxLQUFLLFdBQVcsRUFBRTtZQUM1QyxLQUFLLEdBQUcsS0FBSyxDQUFDLENBQUMsQ0FBQyxDQUFDO1NBQ2xCO0lBQ0gsQ0FBQyxDQUFDLENBQUM7SUFFSCxPQUFPLE9BQU8sS0FBSyxLQUFLLFdBQVcsQ0FBQyxDQUFDLENBQUMsWUFBWSxDQUFDLENBQUMsQ0FBQyxLQUFLLENBQUM7QUFDN0QsQ0FBQyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IGNsb25lRGVlcCB9IGZyb20gJ2xvZGFzaCc7XG5cbi8qKlxuICogRXh0ZW5kaW5nIG9iamVjdCB0aGF0IGVudGVyZWQgaW4gZmlyc3QgYXJndW1lbnQuXG4gKlxuICogUmV0dXJucyBleHRlbmRlZCBvYmplY3Qgb3IgZmFsc2UgaWYgaGF2ZSBubyB0YXJnZXQgb2JqZWN0IG9yIGluY29ycmVjdCB0eXBlLlxuICpcbiAqIElmIHlvdSB3aXNoIHRvIGNsb25lIHNvdXJjZSBvYmplY3QgKHdpdGhvdXQgbW9kaWZ5IGl0KSwganVzdCB1c2UgZW1wdHkgbmV3XG4gKiBvYmplY3QgYXMgZmlyc3QgYXJndW1lbnQsIGxpa2UgdGhpczpcbiAqICAgZGVlcEV4dGVuZCh7fSwgeW91ck9ial8xLCBbeW91ck9ial9OXSk7XG4gKi9cbmV4cG9ydCBjb25zdCBkZWVwRXh0ZW5kID0gZnVuY3Rpb24oLi4ub2JqZWN0czogQXJyYXk8YW55Pik6IGFueSB7XG4gIGlmIChhcmd1bWVudHMubGVuZ3RoIDwgMSB8fCB0eXBlb2YgYXJndW1lbnRzWzBdICE9PSAnb2JqZWN0Jykge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuXG4gIGlmIChhcmd1bWVudHMubGVuZ3RoIDwgMikge1xuICAgIHJldHVybiBhcmd1bWVudHNbMF07XG4gIH1cblxuICBjb25zdCB0YXJnZXQgPSBhcmd1bWVudHNbMF07XG5cbiAgLy8gY29udmVydCBhcmd1bWVudHMgdG8gYXJyYXkgYW5kIGN1dCBvZmYgdGFyZ2V0IG9iamVjdFxuICBjb25zdCBhcmdzID0gQXJyYXkucHJvdG90eXBlLnNsaWNlLmNhbGwoYXJndW1lbnRzLCAxKTtcblxuICBsZXQgdmFsLCBzcmM7XG5cbiAgYXJncy5mb3JFYWNoKChvYmo6IGFueSkgPT4ge1xuICAgIC8vIHNraXAgYXJndW1lbnQgaWYgaXQgaXMgYXJyYXkgb3IgaXNuJ3Qgb2JqZWN0XG4gICAgaWYgKHR5cGVvZiBvYmogIT09ICdvYmplY3QnIHx8IEFycmF5LmlzQXJyYXkob2JqKSkge1xuICAgICAgcmV0dXJuO1xuICAgIH1cblxuICAgIE9iamVjdC5rZXlzKG9iaikuZm9yRWFjaChmdW5jdGlvbiAoa2V5KSB7XG4gICAgICBzcmMgPSB0YXJnZXRba2V5XTsgLy8gc291cmNlIHZhbHVlXG4gICAgICB2YWwgPSBvYmpba2V5XTsgLy8gbmV3IHZhbHVlXG5cbiAgICAgIC8vIHJlY3Vyc2lvbiBwcmV2ZW50aW9uXG4gICAgICBpZiAodmFsID09PSB0YXJnZXQpIHtcbiAgICAgICAgcmV0dXJuO1xuXG4gICAgICAgIC8qKlxuICAgICAgICAgKiBpZiBuZXcgdmFsdWUgaXNuJ3Qgb2JqZWN0IHRoZW4ganVzdCBvdmVyd3JpdGUgYnkgbmV3IHZhbHVlXG4gICAgICAgICAqIGluc3RlYWQgb2YgZXh0ZW5kaW5nLlxuICAgICAgICAgKi9cbiAgICAgIH0gZWxzZSBpZiAodHlwZW9mIHZhbCAhPT0gJ29iamVjdCcgfHwgdmFsID09PSBudWxsKSB7XG4gICAgICAgIHRhcmdldFtrZXldID0gdmFsO1xuICAgICAgICByZXR1cm47XG5cbiAgICAgICAgLy8ganVzdCBjbG9uZSBhcnJheXMgKGFuZCByZWN1cnNpdmUgY2xvbmUgb2JqZWN0cyBpbnNpZGUpXG4gICAgICB9IGVsc2UgaWYgKEFycmF5LmlzQXJyYXkodmFsKSkge1xuICAgICAgICB0YXJnZXRba2V5XSA9IGNsb25lRGVlcCh2YWwpO1xuICAgICAgICByZXR1cm47XG5cbiAgICAgICAgLy8gb3ZlcndyaXRlIGJ5IG5ldyB2YWx1ZSBpZiBzb3VyY2UgaXNuJ3Qgb2JqZWN0IG9yIGFycmF5XG4gICAgICB9IGVsc2UgaWYgKHR5cGVvZiBzcmMgIT09ICdvYmplY3QnIHx8IHNyYyA9PT0gbnVsbCB8fCBBcnJheS5pc0FycmF5KHNyYykpIHtcbiAgICAgICAgdGFyZ2V0W2tleV0gPSBkZWVwRXh0ZW5kKHt9LCB2YWwpO1xuICAgICAgICByZXR1cm47XG5cbiAgICAgICAgLy8gc291cmNlIHZhbHVlIGFuZCBuZXcgdmFsdWUgaXMgb2JqZWN0cyBib3RoLCBleHRlbmRpbmcuLi5cbiAgICAgIH0gZWxzZSB7XG4gICAgICAgIHRhcmdldFtrZXldID0gZGVlcEV4dGVuZChzcmMsIHZhbCk7XG4gICAgICAgIHJldHVybjtcbiAgICAgIH1cbiAgICB9KTtcbiAgfSk7XG5cbiAgcmV0dXJuIHRhcmdldDtcbn07XG5cbmV4cG9ydCBjbGFzcyBEZWZlcnJlZCB7XG5cbiAgcHJvbWlzZTogUHJvbWlzZTxhbnk+O1xuXG4gIHJlc29sdmU6IGFueTtcbiAgcmVqZWN0OiBhbnk7XG5cbiAgY29uc3RydWN0b3IoKSB7XG4gICAgdGhpcy5wcm9taXNlID0gbmV3IFByb21pc2UoKHJlc29sdmUsIHJlamVjdCkgPT4ge1xuICAgICAgdGhpcy5yZXNvbHZlID0gcmVzb2x2ZTtcbiAgICAgIHRoaXMucmVqZWN0ID0gcmVqZWN0O1xuICAgIH0pO1xuICB9XG59XG5cbi8vIGdldERlZXBGcm9tT2JqZWN0KHtyZXN1bHQ6IHtkYXRhOiAxfX0sICdyZXN1bHQuZGF0YScsIDIpOyAvLyByZXR1cm5zIDFcbmV4cG9ydCBmdW5jdGlvbiBnZXREZWVwRnJvbU9iamVjdChvYmplY3QgPSB7fSwgbmFtZTogc3RyaW5nLCBkZWZhdWx0VmFsdWU/OiBhbnkpIHtcbiAgY29uc3Qga2V5cyA9IG5hbWUuc3BsaXQoJy4nKTtcbiAgLy8gY2xvbmUgdGhlIG9iamVjdFxuICBsZXQgbGV2ZWwgPSBkZWVwRXh0ZW5kKHt9LCBvYmplY3QpO1xuICBrZXlzLmZvckVhY2goKGspID0+IHtcbiAgICBpZiAobGV2ZWwgJiYgdHlwZW9mIGxldmVsW2tdICE9PSAndW5kZWZpbmVkJykge1xuICAgICAgbGV2ZWwgPSBsZXZlbFtrXTtcbiAgICB9XG4gIH0pO1xuXG4gIHJldHVybiB0eXBlb2YgbGV2ZWwgPT09ICd1bmRlZmluZWQnID8gZGVmYXVsdFZhbHVlIDogbGV2ZWw7XG59XG4iXX0=