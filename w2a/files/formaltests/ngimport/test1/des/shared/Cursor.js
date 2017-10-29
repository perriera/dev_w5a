"use strict";
/**
 * Created by perry on 2017-07-24.
 */
exports.__esModule = true;
var Cursor = (function () {
    function Cursor(pointer, limit) {
        this.pointer = pointer;
        this.limit = limit;
    }
    Cursor.prototype.pageUp = function () {
        this.pointer -= this.limit;
    };
    Cursor.prototype.pageDown = function () {
        this.pointer += this.limit;
    };
    return Cursor;
}());
exports.Cursor = Cursor;
//# sourceMappingURL=Cursor.js.map