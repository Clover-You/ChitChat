/**
 * <p>
 * 消息窗口
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-23 17:59
 */

import React from 'react'
import { Panel, PanelGroup, PanelResizeHandle } from 'react-resizable-panels'

export const ChatWindow = React.memo(function ChatWindow() {
  const contentList = []

  for (let i = 1; i <= 100; i++) {
    const content = <p>content {i}</p>
    contentList.push(content)
  }

  return (
    <div className="w-full h-full bg-zinc-950/50 flex flex-col container backgroundImage">
      <div
        className="border-b w-full title-height flex items-center px-5"
        style={{
          borderBottomColor: 'rgb(var(--background-end-rgb))',
        }}>
        <h3>荒山野岭（8）</h3>
      </div>

      <PanelGroup direction="vertical">
        <Panel>
          <div className="h-full overflow-auto">{contentList}</div>
        </Panel>

        <PanelResizeHandle>
          <div className="w-full h-[4px] relative top-[2px] cursor-row-resize">
            {' '}
          </div>
        </PanelResizeHandle>

        <Panel
          defaultSize={40}
          maxSize={65}
          minSize={25}>
          <div className="flex-shrink-0 h-full border-t-slate-300/10 border-t-[1px]">
            <div
              contentEditable
              className="px-4 h-full w-full max-h-full overflow-auto cursor-auto outline-none"
              spellCheck={false}></div>
          </div>
        </Panel>
      </PanelGroup>
    </div>
  )
})
