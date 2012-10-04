USE [TIP]
GO
ALTER TABLE [dbo].[PATTERNS] DROP CONSTRAINT [FK_PATTERNS_ROLE]
GO
ALTER TABLE [dbo].[PATTERNS] DROP CONSTRAINT [FK__PATTERNS__Patter__1CF15040]
GO
ALTER TABLE [dbo].[OIM] DROP CONSTRAINT [FK_OIM_TIP]
GO
ALTER TABLE [dbo].[OIM] DROP CONSTRAINT [FK__OIM__Commodity_I__21B6055D]
GO
/****** Object:  Table [dbo].[TIP]    Script Date: 7/26/2012 3:36:49 PM ******/
DROP TABLE [dbo].[TIP]
GO
/****** Object:  Table [dbo].[ROLE]    Script Date: 7/26/2012 3:36:49 PM ******/
DROP TABLE [dbo].[ROLE]
GO
/****** Object:  Table [dbo].[PATTERNS]    Script Date: 7/26/2012 3:36:49 PM ******/
DROP TABLE [dbo].[PATTERNS]
GO
/****** Object:  Table [dbo].[OIM]    Script Date: 7/26/2012 3:36:49 PM ******/
DROP TABLE [dbo].[OIM]
GO
/****** Object:  Table [dbo].[COMMODITY]    Script Date: 7/26/2012 3:36:49 PM ******/
DROP TABLE [dbo].[COMMODITY]
GO
/****** Object:  Table [dbo].[COMMODITY]    Script Date: 7/26/2012 3:36:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[COMMODITY](
	[Commodity_Id] [varchar](260) NOT NULL,
	[Commodity_Name] [varchar](250) NULL,
	[RDL_Class] [varchar](250) NOT NULL,
 CONSTRAINT [PK_COMMODITY] PRIMARY KEY CLUSTERED 
(
	[Commodity_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OIM]    Script Date: 7/26/2012 3:36:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OIM](
	[Pattern_Id] [varchar](260) NOT NULL,
	[Commodity_Id] [varchar](260) NOT NULL,
 CONSTRAINT [PK_OIM] PRIMARY KEY CLUSTERED 
(
	[Pattern_Id] ASC,
	[Commodity_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PATTERNS]    Script Date: 7/26/2012 3:36:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PATTERNS](
	[Pattern_Id] [varchar](260) NOT NULL,
	[Ordinal] [int] NOT NULL,
	[Template_Id] [varchar](260) NOT NULL,
	[Template_Name] [varchar](250) NULL,
	[Seq_Role] [int] NOT NULL,
 CONSTRAINT [PK_PATTERNS] PRIMARY KEY CLUSTERED 
(
	[Pattern_Id] ASC,
	[Ordinal] ASC,
	[Template_Id] ASC,
	[Seq_Role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ROLE]    Script Date: 7/26/2012 3:36:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ROLE](
	[Seq_Role] [int] NOT NULL,
	[Role_Id] [varchar](260) NOT NULL,
	[Role_Name] [varchar](250) NULL,
	[Role_Type] [varchar](100) NOT NULL,
	[Class_id] [varchar](260) NULL,
	[Individual] [varchar](500) NULL,
	[Role_Filler] [varchar](260) NULL,
 CONSTRAINT [PK_ROLE_1] PRIMARY KEY CLUSTERED 
(
	[Seq_Role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TIP]    Script Date: 7/26/2012 3:36:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TIP](
	[Pattern_Id] [varchar](260) NOT NULL,
	[Property_Name] [varchar](250) NULL,
	[Tip_Name] [varchar](250) NOT NULL,
	[Description] [varchar](2000) NULL,
	[Typical_value] [varchar](200) NULL,
	[Level] [varchar](200) NULL,
 CONSTRAINT [PK_Tip] PRIMARY KEY CLUSTERED 
(
	[Pattern_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[COMMODITY] ([Commodity_Id], [Commodity_Name], [RDL_Class]) VALUES (N'100', N'Document', N'DOCUMENT')
INSERT [dbo].[COMMODITY] ([Commodity_Id], [Commodity_Name], [RDL_Class]) VALUES (N'101', N'Equipment', N'EQUIPMENT')
INSERT [dbo].[COMMODITY] ([Commodity_Id], [Commodity_Name], [RDL_Class]) VALUES (N'102', N'Instrument', N'INSTRUMENT')
INSERT [dbo].[COMMODITY] ([Commodity_Id], [Commodity_Name], [RDL_Class]) VALUES (N'103', N'Line', N'PIPING NETWORK')
INSERT [dbo].[OIM] ([Pattern_Id], [Commodity_Id]) VALUES (N'P0001', N'101')
INSERT [dbo].[OIM] ([Pattern_Id], [Commodity_Id]) VALUES (N'P0001', N'102')
INSERT [dbo].[OIM] ([Pattern_Id], [Commodity_Id]) VALUES (N'P0001', N'103')
INSERT [dbo].[OIM] ([Pattern_Id], [Commodity_Id]) VALUES (N'P0002', N'101')
INSERT [dbo].[OIM] ([Pattern_Id], [Commodity_Id]) VALUES (N'P0002', N'102')
INSERT [dbo].[OIM] ([Pattern_Id], [Commodity_Id]) VALUES (N'P0002', N'103')
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0001', 0, N'R30193386273', N'ClassifiedIdentification', 100004)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0001', 0, N'R30193386273', N'ClassifiedIdentification', 100005)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0001', 0, N'R30193386273', N'ClassifiedIdentification', 100006)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0002', 0, N'R48396789128', N'ClassifiedArrangementsOfIndividual', 100007)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0002', 0, N'R48396789128', N'ClassifiedArrangementsOfIndividual', 100008)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0002', 0, N'R48396789128', N'ClassifiedArrangementsOfIndividual', 100009)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0002', 1, N'R30193386273', N'ClassifiedIdentification', 100010)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0002', 1, N'R30193386273', N'ClassifiedIdentification', 100011)
INSERT [dbo].[PATTERNS] ([Pattern_Id], [Ordinal], [Template_Id], [Template_Name], [Seq_Role]) VALUES (N'P0002', 1, N'R30193386273', N'ClassifiedIdentification', 100012)
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100004, N'R30790108016', N'hasIdentificationType', N'Reference', NULL, NULL, NULL)
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100005, N'R44537504070', N'hasObject', N'Possessor', N'', N'', N'')
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100006, N'R22674749688', N'valIdentifier', N'Property', N'', N'', N'')
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100007, N'R89863638836', N'hasArrangementType', N'Reference', N'R49658319833', NULL, NULL)
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100008, N'R58951773002', N'hasPart', N'Possessor', N'', N'', N'')
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100009, N'R85898998707', N'hasWhole', N'Reference', N'', N'', N'')
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100010, N'R30790108016', N'hasIdentificationType', N'Reference', N'R94542781188', NULL, NULL)
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100011, N'R44537504070', N'hasObject', N'Possessor', N'', N'', N'')
INSERT [dbo].[ROLE] ([Seq_Role], [Role_Id], [Role_Name], [Role_Type], [Class_id], [Individual], [Role_Filler]) VALUES (100012, N'R22674749688', N'valIdentifier', N'Property', N'', N'', N'')
INSERT [dbo].[TIP] ([Pattern_Id], [Property_Name], [Tip_Name], [Description], [Typical_value], [Level]) VALUES (N'P0001', N'cell_Name', N'Cell name', N'CAD system cell name (e.g. Intergraph AABBCC code)', N'6QTR13', NULL)
INSERT [dbo].[TIP] ([Pattern_Id], [Property_Name], [Tip_Name], [Description], [Typical_value], [Level]) VALUES (N'P0002', NULL, N'Functional Area', N'An area which is a subdivision of a plant by functional areas', N'35', NULL)
INSERT [dbo].[TIP] ([Pattern_Id], [Property_Name], [Tip_Name], [Description], [Typical_value], [Level]) VALUES (N'P0008', N'tag', N'Tag', N'A unique identifier for an object within a context such as a plant
', N'HX-1234', NULL)
ALTER TABLE [dbo].[OIM]  WITH CHECK ADD  CONSTRAINT [FK__OIM__Commodity_I__21B6055D] FOREIGN KEY([Commodity_Id])
REFERENCES [dbo].[COMMODITY] ([Commodity_Id])
GO
ALTER TABLE [dbo].[OIM] CHECK CONSTRAINT [FK__OIM__Commodity_I__21B6055D]
GO
ALTER TABLE [dbo].[OIM]  WITH CHECK ADD  CONSTRAINT [FK_OIM_TIP] FOREIGN KEY([Pattern_Id])
REFERENCES [dbo].[TIP] ([Pattern_Id])
GO
ALTER TABLE [dbo].[OIM] CHECK CONSTRAINT [FK_OIM_TIP]
GO
ALTER TABLE [dbo].[PATTERNS]  WITH CHECK ADD  CONSTRAINT [FK__PATTERNS__Patter__1CF15040] FOREIGN KEY([Pattern_Id])
REFERENCES [dbo].[TIP] ([Pattern_Id])
GO
ALTER TABLE [dbo].[PATTERNS] CHECK CONSTRAINT [FK__PATTERNS__Patter__1CF15040]
GO
ALTER TABLE [dbo].[PATTERNS]  WITH CHECK ADD  CONSTRAINT [FK_PATTERNS_ROLE] FOREIGN KEY([Seq_Role])
REFERENCES [dbo].[ROLE] ([Seq_Role])
GO
ALTER TABLE [dbo].[PATTERNS] CHECK CONSTRAINT [FK_PATTERNS_ROLE]
GO
