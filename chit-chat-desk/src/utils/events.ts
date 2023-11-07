/**
 * <p>
 * 事件工具
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 15:08
 */

/**
 * 当前事件是 shift + enter 组合键
 * @param event 键盘事件
 */
export function isShiftEnter(event: React.KeyboardEvent) {
  return event.shiftKey && isEnter(event)
}

/**
 * 当前事件是否是 enter 键
 * @param event 键盘事件
 */
export function isEnter(event: React.KeyboardEvent) {
  return event.key === 'Enter' && event.code === 'Enter'
}

/**
 * 当前事件是否是一个 shift 键
 * @param event 键盘事件
 */
export function isShift(event: React.KeyboardEvent) {
  const lrShift = event.code === 'ShiftLeft' || event.code === 'ShiftRight'
  return event.shiftKey === true && lrShift && event.key === 'Shift'
}
