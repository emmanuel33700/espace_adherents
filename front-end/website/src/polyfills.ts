/**
 * This file includes polyfills needed by Angular and is loaded before the app.
 * Modern browsers no longer need the legacy polyfills (core-js, classlist.js,
 * web-animations-js) that were used by ngx-admin.
 */
import 'zone.js';  // Included with Angular CLI.

if (typeof SVGElement.prototype.contains === 'undefined') {
  SVGElement.prototype.contains = HTMLDivElement.prototype.contains;
}
