/**
 * @license
 * Copyright 2000-2023 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
import VectorSource from 'ol/source/Vector';

/**
 * Simple lookup for OL instances that are used by Map.
 * This implementation will never release references, which means that OL
 * instances in the lookup can never be garbage collected as long as the
 * Map element exists.
 * This should only be used as a fallback if a browser does not support
 * weak references and finalization registry.
 */
class SimpleLookup {
  constructor() {
    this.map = new Map();
  }

  get(id) {
    return this.map.get(id);
  }

  put(id, instance) {
    this.map.set(id, instance);
  }
}

/**
 * Lookup for OL instances that uses weak references for storing objects.
 * Using weak references allows the browser to garbage collect the OL
 * instances that are not used anymore in the Map. The lookup also uses a
 * finalization registry to remove weak references from the lookup when
 * instances are garbage collected.
 */
class WeakReferenceLookup {
  constructor() {
    this.map = new Map();
    // Create registry that notifies when a reference is garbage collected,
    // the callback removes the WeakRef entry from the map
    this.registry = new FinalizationRegistry((id) => {
      this.map.delete(id);
    });
  }

  get(id) {
    const weakRef = this.map.get(id);
    return weakRef ? weakRef.deref() : undefined;
  }

  put(id, instance) {
    // Skip if reference is already tracked
    if (this.map.has(id)) return;
    // Store weak reference in map
    const ref = new WeakRef(instance);
    this.map.set(id, ref);
    // Track reference for garbage collection, so that we can clean up the map entry
    this.registry.register(instance, id);
  }
}

const supportsWeakReferenceLookup = window.WeakRef && window.FinalizationRegistry;

/**
 * Creates a lookup that is supported by the browser
 * @returns {WeakReferenceLookup|SimpleLookup}
 */
export function createLookup() {
  return supportsWeakReferenceLookup ? new WeakReferenceLookup() : new SimpleLookup();
}

/**
 * Searches an OpenLayers map instance for the layer whose source contains a specific feature
 * @param layers the array of layers configured in the map
 * @param feature the feature that should be contained in the layers source
 * @returns {*} the layer that contains the feature, or undefined
 */
export function getLayerForFeature(layers, feature) {
  return layers.find((layer) => {
    const source = layer.getSource && layer.getSource();
    const isVectorSource = source && source instanceof VectorSource;

    return isVectorSource && source.getFeatures().includes(feature);
  });
}
