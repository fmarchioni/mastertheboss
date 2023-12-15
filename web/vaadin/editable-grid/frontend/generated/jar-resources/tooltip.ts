import { Tooltip } from '@vaadin/tooltip/src/vaadin-tooltip.js';

const _window = window as any;
_window.Vaadin = _window.Vaadin || {};
_window.Vaadin.Flow = _window.Vaadin.Flow || {};

_window.Vaadin.Flow.tooltip = {
  setDefaultHideDelay: (hideDelay: number) => Tooltip.setDefaultHideDelay(hideDelay),
  setDefaultFocusDelay: (focusDelay: number) => Tooltip.setDefaultFocusDelay(focusDelay),
  setDefaultHoverDelay: (hoverDelay: number) => Tooltip.setDefaultHoverDelay(hoverDelay),
}
