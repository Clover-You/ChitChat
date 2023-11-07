/**
 * <p>
 * events 文件测试用例
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 15:19
 */
import React from 'react'
import { describe, it, expect } from 'vitest'

import { isEnter, isShiftEnter, isShift } from '../events'

function mockEvent(e: Record<string, any>): React.KeyboardEvent {
  return e as unknown as React.KeyboardEvent
}

describe('events', () => {
  it('isEnter', () => {
    expect(
      isEnter(
        mockEvent({
          code: 'Enter',
          key: 'Enter',
          shiftKey: true,
        })
      )
    ).toBe(true)

    expect(
      isEnter(
        mockEvent({
          code: 'Shift',
          key: 'Enter',
          shiftKey: true,
        })
      )
    ).toBe(false)

    expect(
      isEnter(
        mockEvent({
          code: 'Enter',
          key: 'Ctrl',
        })
      )
    ).toBe(false)
  })

  it('isShift', () => {
    expect(
      isShift(
        mockEvent({
          code: 'ShiftRight',
          key: 'Shift',
          shiftKey: true,
        })
      )
    ).toBe(true)

    expect(
      isShift(
        mockEvent({
          code: 'ShiftLeft',
          key: 'Shift',
          shiftKey: true,
        })
      )
    ).toBe(true)

    expect(
      isShift(
        mockEvent({
          code: 'ShiftRight',
          key: 'Shift',
        })
      )
    ).toBe(false)

    expect(
      isShift(
        mockEvent({
          code: 'ShiftLeft',
          key: 'Shift',
        })
      )
    ).toBe(false)

    expect(
      isShift(
        mockEvent({
          code: 'ShiftRight',
          key: 'Shifts',
        })
      )
    ).toBe(false)

    expect(
      isShift(
        mockEvent({
          code: 'ShiftLeft',
          key: 'Shifts',
        })
      )
    ).toBe(false)

    expect(
      isShift(
        mockEvent({
          code: 'ShiftTop',
          key: 'Shift',
        })
      )
    ).toBe(false)
  })

  it('isShiftEnter', () => {
    expect(
      isShiftEnter(
        mockEvent({
          code: 'Enter',
          key: 'Enter',
          shiftKey: true,
        })
      )
    ).toBe(true)

    expect(
      isShiftEnter(
        mockEvent({
          code: 'Enter',
          key: 'Enter',
          shiftKey: false,
        })
      )
    ).toBe(false)

    expect(
      isShiftEnter(
        mockEvent({
          code: 'Shift',
          key: 'Enter',
          shiftKey: true,
        })
      )
    ).toBe(false)

    expect(
      isShiftEnter(
        mockEvent({
          code: 'Enter',
          key: 'Shift',
          shiftKey: true,
        })
      )
    ).toBe(false)
  })
})
