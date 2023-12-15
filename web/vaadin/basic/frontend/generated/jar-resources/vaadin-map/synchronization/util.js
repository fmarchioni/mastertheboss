/**
 * @license
 * Copyright 2000-2023 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */

/**
 * Helper to convert a coordinate object with the shape { x: number, y: number}
 * into a coordinate array used by OpenLayers
 * @param coordinate
 * @returns {*[]}
 */
export function convertToCoordinateArray(coordinate) {
  return [coordinate.x, coordinate.y];
}

/**
 * Helper to convert a size object with the shape { width: number, height: number}
 * into a size array used by OpenLayers
 * @param size
 * @returns {*[]}
 */
export function convertToSizeArray(size) {
  return [size.width, size.height];
}

/**
 * Synchronizes an OpenLayers collection with data from a Javascript array
 */
export function synchronizeCollection(collection, updatedIds, options) {
  // Check if we have changes
  const hasChanges =
    updatedIds.length !== collection.getLength() ||
    collection.getArray().some((existingItem, index) => existingItem.id !== updatedIds[index]);
  // Skip if there aren't any changes
  if (!hasChanges) return;
  // Get instance references from ids, these must have been synchronized earlier
  const updatedItems = updatedIds
    .map((id) => options.lookup.get(id))
    // This shouldn't be necessary, but having this safe-guard allows us to
    // at least continue the sync in case a reference is missing
    .filter((item) => !!item);
  // Rebuild the collection
  // It shouldn't matter whether we just add/move/remove specific items, or rebuild
  // the whole thing, this will result in several change events anyway
  collection.clear();
  updatedItems.forEach((item) => collection.push(item));
}

/**
 * Creates an options object from a configuration object.
 * This clones the configuration object and removes any properties that have the
 * value `null`, as OpenLayers requires the use of `undefined` for properties
 * that should not be set.
 * @param configurationObject
 * @returns {*}
 */
export function createOptions(configurationObject) {
  const options = { ...configurationObject };
  Object.keys(configurationObject).forEach((key) => {
    const value = configurationObject[key];

    if (value === null) {
      delete options[key];
    }
  });
  return options;
}
