'use client'
/**
 * <p>
 * 聊天界面
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-23 14:08
 */
import { PanelGroup, Panel, PanelResizeHandle } from 'react-resizable-panels'

import { ChatPanel } from '#/components/ChatPanel'
import { ChatWindow } from '#/components/Chat/ChatWindow'

export default function ChatPage() {
  return (
    <div className={'h-full w-full'}>
      <PanelGroup
        className="w-full"
        autoSaveId="example"
        direction="horizontal">
        <Panel
          defaultSize={25}
          minSize={25}
          maxSize={50}>
          <ChatPanel />
        </Panel>

        <PanelResizeHandle>
          <div className="w-2 h-full cursor-col-resize"></div>
        </PanelResizeHandle>

        <Panel>
          <ChatWindow />
        </Panel>
      </PanelGroup>
    </div>
  )
}
